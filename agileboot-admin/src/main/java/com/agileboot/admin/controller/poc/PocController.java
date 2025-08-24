package com.agileboot.admin.controller.poc;

import cn.hutool.core.collection.ListUtil;
import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.poc.PocApplicationService;
import com.agileboot.domain.poc.command.AddPocCommand;
import com.agileboot.domain.poc.command.UpdatePocCommand;
import com.agileboot.domain.poc.db.SearchPocDO;
import com.agileboot.domain.poc.dto.PocDTO;
import com.agileboot.domain.poc.query.PocQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Poc项目汇总表
 * @author fisher
 */
@Tag(name = "POC项目汇总表API", description = "POC项目汇总表相关的增删查改")
@RestController
@RequestMapping("/poc")
@RequiredArgsConstructor
public class PocController extends BaseController {

    private final PocApplicationService pocApplicationService;

    /**
     * 获取用户列表
     */
    @Operation(summary = "POC列表")
    @PreAuthorize("@permission.has('poc:list')")
    @GetMapping
    public ResponseDTO<PageDTO<PocDTO>> pocList(PocQuery<SearchPocDO> query) {
        PageDTO<PocDTO> page = pocApplicationService.getPocList(query);
        return ResponseDTO.ok(page);
    }

    @Operation(summary = "POC列表导出")
    @AccessLog(title = "POC项目管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('poc:export')")
    @GetMapping("/excel")
    public void exportPocByExcel(HttpServletResponse response, PocQuery<SearchPocDO> query) {
        PageDTO<PocDTO> pocList = pocApplicationService.getPocList(query);
        CustomExcelUtil.writeToResponse(pocList.getRows(), PocDTO.class, response);
    }

    @Operation(summary = "POC列表导入")
    @AccessLog(title = "POC项目管理", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("@permission.has('poc:import')")
    @PostMapping("/excel")
    public ResponseDTO<Void> importPocByExcel(MultipartFile file) {
        List<AddPocCommand> commands = CustomExcelUtil.readFromRequest(AddPocCommand.class, file);

        for (AddPocCommand command : commands) {
            pocApplicationService.addPoc(command);
        }
        return ResponseDTO.ok();
    }

    /**
     * 下载批量导入模板
     */
    @Operation(summary = "POC导入excel下载")
    @GetMapping("/excelTemplate")
    public void downloadExcelTemplate(HttpServletResponse response) {
        CustomExcelUtil.writeToResponse(ListUtil.toList(new AddPocCommand()), AddPocCommand.class, response);
    }

    /**
     * 根据POC编号获取详细信息
     */
    @Operation(summary = "POC详情")
    @PreAuthorize("@permission.has('poc:query')")
    @GetMapping("/{pocId}")
    public ResponseDTO<PocDTO> getPocInfo(@PathVariable(value = "pocId", required = false) Long pocId) {
        PocDTO pocInfo = pocApplicationService.getPocInfo(pocId);
        return ResponseDTO.ok(pocInfo);
    }

    /**
     * 新增POC
     */
    @Operation(summary = "新增POC")
    @PreAuthorize("@permission.has('poc:add')")
    @AccessLog(title = "POC项目管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@Validated @RequestBody AddPocCommand command) {
        pocApplicationService.addPoc(command);
        return ResponseDTO.ok();
    }

    /**
     * 修改POC
     */
    @Operation(summary = "修改POC")
    @PreAuthorize("@permission.has('poc:edit')")
    @AccessLog(title = "POC项目管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{pocId}")
    public ResponseDTO<Void> edit(@Validated @RequestBody UpdatePocCommand command) {
        pocApplicationService.updatePoc(command);
        return ResponseDTO.ok();
    }

    /**
     * 删除POC
     */
    @Operation(summary = "删除POC")
    @PreAuthorize("@permission.has('poc:remove')")
    @AccessLog(title = "POC项目管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{pocIds}")
    public ResponseDTO<Void> remove(@RequestParam @NotNull @NotEmpty List<Long> pocIds) {
        pocApplicationService.deletePocs(new BulkOperationCommand<>(pocIds));
        return ResponseDTO.ok();
    }

}
