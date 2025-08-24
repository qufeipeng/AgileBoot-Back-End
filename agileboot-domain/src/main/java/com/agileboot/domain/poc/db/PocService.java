package com.agileboot.domain.poc.db;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.role.db.SysRoleEntity;
import com.agileboot.domain.system.user.db.SearchUserDO;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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
     * 校验岗位名称
     * @param pocId POC ID
     * @param project 项目名称
     * @return 结果
     */
    boolean isProjectDuplicated(Long pocId, String project);

    /**
     * 根据条件分页查询用户列表
     *
     * @param query 查询参数
     * @return 用户信息集合信息
     */
    Page<SearchPocDO> getPocList(AbstractPageQuery<SearchPocDO> query);
}
