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
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行Service业务层处理
 *
 * @author 李健
 * @date 2023-07-03
 */
@RequiredArgsConstructor
@Service
public class MqttAclServiceImpl implements IMqttAclService {

    private final MqttAclMapper baseMapper;

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @Override
    public MqttAclVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @Override
    public TableDataInfo<MqttAclVo> queryPageList(MqttAclBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MqttAcl> lqw = buildQueryWrapper(bo);
        Page<MqttAclVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @Override
    public List<MqttAclVo> queryList(MqttAclBo bo) {
        LambdaQueryWrapper<MqttAcl> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MqttAcl> buildQueryWrapper(MqttAclBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MqttAcl> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), MqttAcl::getUsername, bo.getUsername());
        lqw.like(StringUtils.isNotBlank(bo.getTopic()), MqttAcl::getTopic, bo.getTopic());
        lqw.eq(bo.getAccess() != null, MqttAcl::getAccess, bo.getAccess());
        lqw.eq(bo.getAllow() != null, MqttAcl::getAllow, bo.getAllow());
        return lqw;
    }

    /**
     * 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @Override
    public Boolean insertByBo(MqttAclBo bo) {
        MqttAcl add = BeanUtil.toBean(bo, MqttAcl.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @Override
    public Boolean updateByBo(MqttAclBo bo) {
        MqttAcl update = BeanUtil.toBean(bo, MqttAcl.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MqttAcl entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
