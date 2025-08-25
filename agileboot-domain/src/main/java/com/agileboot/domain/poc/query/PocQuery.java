package com.agileboot.domain.poc.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.infrastructure.user.AuthenticationUtils;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PocQuery<T> extends AbstractPageQuery<T> {

    private String customer;
    private String project;
    private String status;
    private String owner;
    private String risk;

    @Override
    public QueryWrapper<T> addQueryCondition() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(customer), "u.customer", customer)
                .like(StrUtil.isNotEmpty(project), "u.project", project)
                .like(StrUtil.isNotEmpty(owner), "u.owner", owner)
                .eq(StrUtil.isNotEmpty(status), "u.status", status)
                .eq(StrUtil.isNotEmpty(risk), "u.risk", risk)
                .eq("u.deleted", 0);

        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        if (!loginUser.isAdmin()) {
            queryWrapper.eq("u.dep_id", loginUser.getDeptId());
        }

        // 当前端没有选择排序字段时，则使用create_time字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("u.create_time");
        }
        this.setTimeRangeColumn("u.create_time");

        return queryWrapper;
    }
}
