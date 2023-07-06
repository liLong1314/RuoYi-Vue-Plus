package com.ruoyi.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.devices.domain.bo.MqttAclBo;
import com.ruoyi.devices.domain.vo.MqttAclVo;
import com.ruoyi.devices.domain.MqttAcl;
import com.ruoyi.devices.mapper.MqttAclMapper;
import com.ruoyi.devices.service.IMqttAclService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * mqttUser的acl规则Service业务层处理
 *
 * @author 李健
 * @date 2023-07-04
 */
@RequiredArgsConstructor
@Service
public class MqttAclServiceImpl implements IMqttAclService {

    private final MqttAclMapper mqttAclMapper;

    /**
     * 查询mqttUser的acl规则
     */
    @Override
    public MqttAclVo queryById(Long id){
        return mqttAclMapper.selectVoById(id);
    }

    /**
     * 查询mqttUser的acl规则列表
     */
    @Override
    public TableDataInfo<MqttAclVo> queryPageList(MqttAclBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MqttAcl> lqw = buildQueryWrapper(bo);
        Page<MqttAclVo> result = mqttAclMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询mqttUser的acl规则列表
     */
    @Override
    public List<MqttAclVo> queryList(MqttAclBo bo) {
        LambdaQueryWrapper<MqttAcl> lqw = buildQueryWrapper(bo);
        return mqttAclMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MqttAcl> buildQueryWrapper(MqttAclBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MqttAcl> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTopic()), MqttAcl::getTopic, bo.getTopic());
        return lqw;
    }

    /**
     * 新增mqttUser的acl规则
     */
    @Override
    public Boolean insertByBo(MqttAclBo bo) {
        MqttAcl add = BeanUtil.toBean(bo, MqttAcl.class);
//      默认允许访问
        if (add.getAllow() == null) {
            add.setAllow(1L);
        }
//第一条
        add.setAccess(1L);
        add.setTopic("/zustse/dev/{"+bo.getUsername()+"}/cmd");
        validEntityBeforeSave(add);
        boolean flag = mqttAclMapper.insert(add) > 0;
//        第二条
        MqttAcl add2 = BeanUtil.toBean(bo, MqttAcl.class);
        if (add2.getAllow() == null) {
            add2.setAllow(1L);
        }
        add2.setAccess(2L);
        add2.setTopic("/zustse/dev/{"+bo.getUsername()+"}/data");
        validEntityBeforeSave(add2);
        boolean flag2 = mqttAclMapper.insert(add2) > 0;
        if (flag) {
            bo.setId(add.getId());

        }
        return flag&&flag2;
    }

    /**
     * 修改mqttUser的acl规则
     */
    @Override
    public Boolean updateByBo(MqttAclBo bo) {
        MqttAcl update = BeanUtil.toBean(bo, MqttAcl.class);
        if (update.getAccess() == 1){
            update.setTopic("/zustse/dev/{"+update.getUsername()+"}/cmd");
        } else if (update.getAccess() == 2) {
            update.setTopic("/zustse/dev/{"+update.getUsername()+"}/data");
        }else {
            update.setTopic("/zustse/dev/{"+update.getUsername()+"}/info");
        }
        validEntityBeforeSave(update);
        return mqttAclMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MqttAcl entity){
        //TODO 做一些数据校验,如唯一约束
//        if (entity.getUsername() != null) {
//            LambdaQueryWrapper<MqttAcl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(MqttAcl::getUsername, entity.getUsername());
//            boolean b = mqttAclMapper.selectCount(lambdaQueryWrapper) > 0;
//            if (b){
//                throw new IllegalArgumentException("重复username");
//            }
//            throw new IllegalArgumentException("不允许访问mqttACL，所以操作不予许");
//        }
//        else
        if (entity.getTopic() == null || entity.getTopic() == "") {
            throw new IllegalArgumentException("未添加主题");
        }

    }

    /**
     * 批量删除mqttUser的acl规则
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return mqttAclMapper.deleteBatchIds(ids) > 0;
    }
}
