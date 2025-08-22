package com.agileboot.domain.poc.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * 查询POC列表
     * @return POC集合信息
     */
    @Select("SELECT u.*, d.dept_name, e.username as create_username, f.username as update_username "
            + "FROM t_poc u "
            + " LEFT JOIN sys_dept d ON u.dept_id = d.dept_id "
            + " LEFT JOIN sys_user e ON u.creator_id = e.user_id "
            + " LEFT JOIN sys_user f ON u.updater_id = f.user_id ")
    List<SearchPocDO> selectPocList();

    /**
     * 查询POC列表
     * @param deptId 部门ID
     * @return POC集合信息
     */
    @Select("SELECT u.*, d.dept_name, e.username as create_username, f.username as update_username "
            + "FROM t_poc u "
            + " LEFT JOIN sys_dept d ON u.dept_id = d.dept_id "
            + " LEFT JOIN sys_user e ON u.creator_id = e.user_id "
            + " LEFT JOIN sys_user f ON u.updater_id = f.user_id "
            + " WHERE u.dept_id = #{deptId} ")
    List<SearchPocDO> selectPocListByDeptId(@Param("deptId") Long deptId);

}
