package com.agileboot.domain.poc.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.poc.db.PocEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PocQuery extends AbstractPageQuery<PocEntity> {

    private String customer;
    private String project;
    private Integer status;

    @Override
    public QueryWrapper<PocEntity> addQueryCondition() {
        QueryWrapper<PocEntity> queryWrapper = new QueryWrapper<PocEntity>()
            .eq(status != null, "status", status)
            .like(StrUtil.isNotEmpty(customer), "customer", customer)
            .like(StrUtil.isNotEmpty(project), "project", project);
        // 当前端没有选择排序字段时，则使用poc_id字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("poc_id");
        }
        this.setTimeRangeColumn("create_time");

        return queryWrapper;
    }
}
