package com.agileboot.domain.poc.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * POC项目汇总表 Mapper 接口
 * </p>
 *
 * @author fisher
 * @since 2025-08-22
 */
public interface PocMapper extends BaseMapper<PocEntity> {

    /**
     * 根据条件分页查询POC列表
     * @param page 页码对象
     * @param queryWrapper 查询对象
     * @return POC集合信息
     */
    @Select("SELECT u.*, d.dept_name, e.username as create_username, f.username as update_username "
            + "FROM t_poc u "
            + " LEFT JOIN sys_dept d ON u.dept_id = d.dept_id "
            + " LEFT JOIN sys_user e ON u.creator_id = e.user_id "
            + " LEFT JOIN sys_user f ON u.updater_id = f.user_id "
            + " ${ew.customSqlSegment}")
    Page<SearchPocDO> getPocList(Page<SearchPocDO> page,
                                   @Param(Constants.WRAPPER) Wrapper<SearchPocDO> queryWrapper);

}
