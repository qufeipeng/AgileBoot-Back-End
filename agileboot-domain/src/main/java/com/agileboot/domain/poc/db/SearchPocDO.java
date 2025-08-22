package com.agileboot.domain.poc.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 如果Entity的字段和复杂查询不匹配时   自定义类来接收
 * @author fisher
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchPocDO extends PocEntity {

    private String deptName;
    private String createUsername;
    private String updateUsername;

}
