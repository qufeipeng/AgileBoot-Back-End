package com.agileboot.domain.poc.dto;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.common.annotation.ExcelSheet;
import com.agileboot.domain.common.cache.CacheCenter;
import com.agileboot.domain.poc.db.PocEntity;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.user.db.SysUserEntity;
import lombok.Data;
import java.util.Date;

/**
 * @author fisher
 */
@Data
@ExcelSheet(name = "POC项目列表")
public class PocDTO {

    public PocDTO(PocEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);

            SysDeptEntity dept = CacheCenter.deptCache.get(entity.getDeptId() + "");
            if (dept != null) {
                this.deptName = dept.getDeptName();
            }

            SysUserEntity creator = CacheCenter.userCache.getObjectById(entity.getCreatorId());
            if (creator != null) {
                this.creatorName = creator.getUsername();
            }

            SysUserEntity updater = CacheCenter.userCache.getObjectById(entity.getUpdaterId());
            if (creator != null) {
                this.creatorName = updater.getUsername();
            }
        }
    }

    @ExcelColumn(name = "POC ID")
    private Long pocId;

    @ExcelColumn(name = "当前责任人")
    private String owner;

    @ExcelColumn(name = "状态")
    private String status;

    @ExcelColumn(name = "客户名称")
    private String customer;

    @ExcelColumn(name = "项目名称")
    private String project;

    @ExcelColumn(name = "进度")
    private int progress;

    @ExcelColumn(name = "风险")
    private String risk;

    @ExcelColumn(name = "待处理&风险描述")
    private String todo_risk;

    @ExcelColumn(name = "已完成进展")
    private String done;

    @ExcelColumn(name = "销售")
    private String sales;

    @ExcelColumn(name = "售前")
    private String sa;

    @ExcelColumn(name = "poc人员")
    private String poc;

    @ExcelColumn(name = "运维")
    private String op;

    @ExcelColumn(name = "重点项目")
    private String kv;

    @ExcelColumn(name = "开始时间")
    private Date pocStartDt;

    @ExcelColumn(name = "POC完成时间")
    private Date pocEndDt;

    @ExcelColumn(name = "上线时间")
    private Date onlineDt;

    @ExcelColumn(name = "最后更新时间")
    private Date lastUpdDt;

    @ExcelColumn(name = "省份")
    private String province;

    @ExcelColumn(name = "行业")
    private String industry;

    @ExcelColumn(name = "isv")
    private String isv;

    @ExcelColumn(name = "运维厂商")
    private String maintenance;

    @ExcelColumn(name = "版本号")
    private String version;

    @ExcelColumn(name = "部署形态")
    private String deployment;

    @ExcelColumn(name = "兼容性需求")
    private String compatibility;

    @ExcelColumn(name = "相关组件")
    private String plugins;

    @ExcelColumn(name = "备注")
    private String notes;

    @ExcelColumn(name = "部门名称")
    private String deptName;

    @ExcelColumn(name = "创建者")
    private String creatorName;

    @ExcelColumn(name = "创建时间")
    private Date createTime;

    @ExcelColumn(name = "更新者")
    private String updaterName;

    @ExcelColumn(name = "更新时间")
    private Date updateTime;
}
