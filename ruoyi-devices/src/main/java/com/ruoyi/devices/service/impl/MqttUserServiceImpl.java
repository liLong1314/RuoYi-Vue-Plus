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
import com.ruoyi.devices.domain.bo.MqttUserBo;
import com.ruoyi.devices.domain.vo.MqttUserVo;
import com.ruoyi.devices.domain.MqttUser;
import com.ruoyi.devices.mapper.MqttUserMapper;
import com.ruoyi.devices.service.IMqttUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Service业务层处理
 *
 * @author 李健
 * @date 2023-07-03
 */
@RequiredArgsConstructor
@Service
public class MqttUserServiceImpl implements IMqttUserService {

    private final MqttUserMapper baseMapper;

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
        lqw.eq(StringUtils.isNotBlank(bo.getPassword()), MqttUser::getPassword, bo.getPassword());
        return lqw;
    }

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public Boolean insertByBo(MqttUserBo bo) {
        MqttUser add = BeanUtil.toBean(bo, MqttUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public Boolean updateByBo(MqttUserBo bo) {
        MqttUser update = BeanUtil.toBean(bo, MqttUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MqttUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
