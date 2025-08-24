package com.agileboot.domain.poc.command;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author valarchie
 */
@Data
public class AddPocCommand {

    private String owner;

    @NotBlank(message = "状态不能为空")
    @Size(max = 100, message = "状态长度不能超过100个字符")
    private String status;

    @NotBlank(message = "客户名称不能为空")
    @Size(max = 200, message = "客户名称长度不能超过200个字符")
    private String customer;

    @NotBlank(message = "项目名称")
    @Size(max = 2000, message = "项目名称长度不能超过2000个字符")
    private String project;

    @PositiveOrZero
    @Min(value = 0, message = "进度必须大于等于0")
    @Max(value = 100, message = "进度必须小于等于100")
    private int progress;

    @NotBlank(message = "风险")
    @Size(max = 20, message = "风险长度不能超过20个字符")
    private String risk;

    private String todo_risk;

    private String done;

    private String sales;

    private String sa;

    private String poc;

    private String op;

    private String kv;

    private Date pocStartDt;

    private Date pocEndDt;

    private Date onlineDt;

    private Date lastUpdDt;

    private String province;

    private String industry;

    private String isv;

    private String maintenance;

    private String version;

    private String deployment;

    private String compatibility;

    private String plugins;

    private String notes;

    @PositiveOrZero
    private long deptId;

}
