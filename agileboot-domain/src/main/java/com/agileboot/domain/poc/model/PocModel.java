package com.agileboot.domain.poc.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.poc.command.AddPocCommand;
import com.agileboot.domain.poc.command.UpdatePocCommand;
import com.agileboot.domain.poc.db.PocEntity;
import com.agileboot.domain.poc.db.PocService;
import com.agileboot.domain.system.dept.model.DeptModelFactory;
import lombok.NoArgsConstructor;

/**
 * @author fisher
 */
@NoArgsConstructor
public class PocModel extends PocEntity {

    private PocService pocService;
    private DeptModelFactory deptModelFactory;

    public PocModel(PocService pocService) {
        this.pocService = pocService;
    }

    public PocModel(PocEntity entity, PocService pocService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.pocService = pocService;
    }

    public void loadFromAddCommand(AddPocCommand addCommand) {
        if (addCommand != null) {
            BeanUtil.copyProperties(addCommand, this, "postId");
        }
    }


    public void loadFromUpdateCommand(UpdatePocCommand command) {
        if (command != null) {
            loadFromAddCommand(command);
        }
    }

    public void checkProjectUnique() {
        if (pocService.isProjectDuplicated(getPocId(), getProject())) {
            throw new ApiException(ErrorCode.Business.PROJECT_IS_NOT_UNIQUE, getProject());
        }
    }

    public void checkFieldRelatedEntityExist() {

        if (getDeptId() != null) {
            deptModelFactory.loadById(getDeptId());
        }

    }
}
