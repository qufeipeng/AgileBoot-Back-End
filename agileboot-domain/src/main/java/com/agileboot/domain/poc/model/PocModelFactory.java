package com.agileboot.domain.poc.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.poc.db.PocEntity;
import com.agileboot.domain.poc.db.PocService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author fisher
 */
@Component
@RequiredArgsConstructor
public class PocModelFactory {

    private final PocService pocService;

    public PocModel loadById(Long pocId) {
        PocEntity byId = pocService.getById(pocId);
        if (byId == null) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, pocId, "POC");
        }
        return new PocModel(byId, pocService);
    }

    public PocModel create() {
        return new PocModel(pocService);
    }

}
