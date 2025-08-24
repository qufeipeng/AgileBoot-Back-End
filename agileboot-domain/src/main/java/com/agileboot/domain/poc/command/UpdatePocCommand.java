package com.agileboot.domain.poc.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdatePocCommand extends AddPocCommand {

    @NotNull(message = "POC ID不能为空")
    @Positive
    private Long pocId;

}
