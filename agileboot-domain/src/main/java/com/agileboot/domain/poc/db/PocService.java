package com.agileboot.domain.poc.db;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.role.db.SysRoleEntity;
import com.agileboot.domain.system.user.db.SearchUserDO;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * POC项目汇总表 服务类
 * </p>
 *
 * @author fisher
 * @since 2022-08-22
 */
public interface PocService extends IService<PocEntity> {


    /**
     * 获取用户的角色
     * @param userId 用户id
     * @return 用户角色
     */
    SysRoleEntity getRoleOfUser(Long userId);

    /**
     * 获取用户的岗位
     * @param userId 用户id
     * @return 用户岗位
     */
    SysPostEntity getPostOfUser(Long userId);

    /**
     * 获取用户的权限列表
     * @param userId 用户id
     * @return 用户菜单权限列表
     */
    Set<String> getMenuPermissionsForUser(Long userId);


    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserEntity getUserByUserName(String userName);


    /**
     * 根据条件分页查询POC列表
     *
     * @param query 查询参数
     * @return POC集合信息
     */
    Page<SearchPocDO> getPocList(AbstractPageQuery<SearchPocDO> query);


}
