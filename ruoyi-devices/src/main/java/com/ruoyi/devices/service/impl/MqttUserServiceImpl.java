package com.ruoyi.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.devices.domain.Device;
import com.ruoyi.devices.domain.MqttAcl;
import com.ruoyi.devices.domain.bo.MqttAclBo;
import com.ruoyi.devices.domain.vo.MqttAclVo;
import com.ruoyi.devices.mapper.MqttAclMapper;
import com.ruoyi.devices.service.IMqttAclService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.devices.domain.bo.MqttUserBo;
import com.ruoyi.devices.domain.vo.MqttUserVo;
import com.ruoyi.devices.domain.MqttUser;
import com.ruoyi.devices.mapper.MqttUserMapper;
import com.ruoyi.devices.service.IMqttUserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Service业务层处理
 *
 * @author 李健
 * @date 2023-07-04
 */
@RequiredArgsConstructor
@Service
public class MqttUserServiceImpl implements IMqttUserService {

    private final MqttUserMapper baseMapper;
    private final IMqttAclService mqttAclService;
    private final MqttAclMapper mqttAclMapper;

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public MqttUserVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @Override
    public TableDataInfo<MqttUserVo> queryPageList(MqttUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MqttUser> lqw = buildQueryWrapper(bo);
        Page<MqttUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @Override
    public List<MqttUserVo> queryList(MqttUserBo bo) {
        LambdaQueryWrapper<MqttUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MqttUser> buildQueryWrapper(MqttUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MqttUser> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), MqttUser::getUsername, bo.getUsername());
        return lqw;
    }

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     * 密码不存在的话暂时把密码初始化为12138
     */
    @Override
    public Boolean insertByBo(MqttUserBo bo) {
       MqttUser add = BeanUtil.toBean(bo,MqttUser.class);
       validEntityBeforeSave(add);
        if (bo.getPassword() == null){
            add.setPassword("12138");
        }
        //hutool加密
        String password = DigestUtil.sha256Hex(bo.getPassword());
        add.setPassword(password);
       boolean flag1 = baseMapper.insert(add)>0;
       boolean flag2 = false;
        //添加用户时给予权限
        //感觉写这里合适点，因为添加的两个权限不一样，写在ACL里不太行
       if (flag1){
           bo.setId(add.getId());
           MqttAclBo mqttAclBo1 = new MqttAclBo();
           MqttAclBo mqttAclBo2 = new MqttAclBo();
           mqttAclBo1.setUsername(bo.getUsername());
           mqttAclBo2.setUsername(bo.getUsername());
           mqttAclBo1.setTopic("/zustse/dev/{"+bo.getUsername()+"}/cmd");
           mqttAclBo2.setTopic("/zustse/dev/{"+bo.getUsername()+"}/data");
           //允许访问
           mqttAclBo1.setAllow(1L);mqttAclBo2.setAllow(1L);
           //权限一允许订阅，权限二允许发布
           mqttAclBo1.setAccess(1L);
           mqttAclBo2.setAccess(2L);
           flag2= (mqttAclService.insertByBo(mqttAclBo1))
               && (mqttAclService.insertByBo(mqttAclBo2));
       }
       return flag1 && flag2;
    }

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public Boolean updateByBo(MqttUserBo bo) {
        MqttUser update = BeanUtil.toBean(bo, MqttUser.class);
        validEntityBeforeSave(update);
        Long id = bo.getId(); //通过id查询修改前的数据
        MqttUser beforeUpdate = baseMapper.selectById(id);
        //若修改用户名，则修改对应的权限username
        if (update.getUsername() != beforeUpdate.getUsername()){
            LambdaQueryWrapper<MqttAcl> lqw = new LambdaQueryWrapper<>();
            lqw.eq(MqttAcl::getUsername,beforeUpdate.getUsername());
            // 通过username 查询所有与用户相关的ACL
            List<MqttAcl> mqttAcls = mqttAclMapper.selectList(lqw);
            //更新mqttAcl的username 利用id对应关系
            for (MqttAcl mqttAcl : mqttAcls){
                mqttAcl.setUsername(bo.getUsername());
                mqttAclMapper.updateById(mqttAcl);
            }
        }

        return baseMapper.updateById(update) > 0 ;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MqttUser entity){
        //TODO 做一些数据校验,如唯一约束
        if (entity.getUsername() == null) {
            throw new  IllegalArgumentException("MqttUser的username为空。");
        }


    }

    /**
     * 批量删除mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
            List<MqttUserVo> mqttUserVos = baseMapper.selectVoBatchIds(ids);
//            baseMapper.selectBatchIds()
//            for ()
            for (MqttUserVo mqttUserVo : mqttUserVos ){
                LambdaQueryWrapper<MqttAcl> lambdaQueryWrapper =new LambdaQueryWrapper<MqttAcl>();
                lambdaQueryWrapper.eq(MqttAcl::getUsername, mqttUserVo.getUsername());
                List<MqttAcl> mqttAcls = mqttAclMapper.selectList(lambdaQueryWrapper);
                List<Long> iids = new ArrayList<>();
                for (MqttAcl mqttAcl:mqttAcls){
                    iids.add(mqttAcl.getId());
//                    mqttAclMapper.deleteById(mqttAcl.getId());
                }
                mqttAclService.deleteWithValidByIds(iids, isValid);
            }
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
