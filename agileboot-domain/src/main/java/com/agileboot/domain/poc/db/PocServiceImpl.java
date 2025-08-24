package com.agileboot.domain.poc.db;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.role.db.SysRoleEntity;
import com.agileboot.domain.system.user.db.SearchUserDO;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.agileboot.domain.system.user.db.SysUserMapper;
import com.agileboot.domain.system.user.db.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * POC项目汇总表 服务实现类
 * </p>
 *
 * @author fisher
 * @since 2025-08-24
 */
@Service
public class PocServiceImpl extends ServiceImpl<PocMapper, PocEntity> implements PocService {


    @Override
    public boolean isProjectDuplicated(Long pocId, String project) {
        QueryWrapper<PocEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(pocId != null, "poc_id", pocId)
                .eq("project", project);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public Page<SearchPocDO> getPocList(AbstractPageQuery<SearchPocDO> query) {
        return baseMapper.getPocList(query.toPage(), query.toQueryWrapper());
    }

}
