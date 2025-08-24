package com.agileboot.domain.poc.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * POC项目汇总表
 * </p>
 *
 * @author fisher
 * @since 2025-08-22
 */
@Getter
@Setter
@TableName("t_poc")
@ApiModel(value = "POC对象", description = "POC项目汇总表")
public class PocEntity extends BaseEntity<PocEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("POC ID")
    @TableId(value = "poc_id", type = IdType.AUTO)
    private Long pocId;

    @ApiModelProperty("当前责任人")
    @TableField("owner")
    private String owner;

    @ApiModelProperty("状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("客户名称")
    @TableField("customer")
    private String customer;

    @ApiModelProperty("项目名称")
    @TableField("project")
    private String project;

    @ApiModelProperty("进度")
    @TableField("progress")
    private int progress;

    @ApiModelProperty("风险")
    @TableField("risk")
    private String risk;

    @ApiModelProperty("待处理&风险描述")
    @TableField("todo_risk")
    private String todo_risk;

    @ApiModelProperty("已完成进展")
    @TableField("done")
    private String done;

    @ApiModelProperty("销售")
    @TableField("sales")
    private String sales;

    @ApiModelProperty("售前")
    @TableField("sa")
    private String sa;

    @ApiModelProperty("poc人员")
    @TableField("poc")
    private String poc;

    @ApiModelProperty("运维")
    @TableField("op")
    private String op;

    @ApiModelProperty("重点项目")
    @TableField("kv")
    private String kv;

    @ApiModelProperty("开始时间")
    @TableField("poc_start_dt")
    private Date pocStartDt;

    @ApiModelProperty("POC完成时间")
    @TableField("poc_end_dt")
    private Date pocEndDt;

    @ApiModelProperty("上线时间")
    @TableField("online_dt")
    private Date onlineDt;

    @ApiModelProperty("最后更新时间")
    @TableField("last_upd_dt")
    private Date lastUpdDt;

    @ApiModelProperty("省份")
    @TableField("province")
    private String province;

    @ApiModelProperty("行业")
    @TableField("industry")
    private String industry;

    @ApiModelProperty("isv")
    @TableField("isv")
    private String isv;

    @ApiModelProperty("运维厂商")
    @TableField("maintenance")
    private String maintenance;

    @ApiModelProperty("版本号")
    @TableField("version")
    private String version;

    @ApiModelProperty("部署形态")
    @TableField("deployment")
    private String deployment;

    @ApiModelProperty("兼容性需求")
    @TableField("compatibility")
    private String compatibility;

    @ApiModelProperty("相关组件")
    @TableField("plugins")
    private String plugins;

    @ApiModelProperty("备注")
    @TableField("notes")
    private String notes;

    @ApiModelProperty("部门ID")
    @TableField("dept_id")
    private Long deptId;

    @Override
    public Serializable pkVal() {
        return this.pocId;
    }

}
