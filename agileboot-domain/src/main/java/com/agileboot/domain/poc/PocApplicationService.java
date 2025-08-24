package com.agileboot.domain.poc;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.poc.command.AddPocCommand;
import com.agileboot.domain.poc.command.UpdatePocCommand;
import com.agileboot.domain.poc.db.PocEntity;
import com.agileboot.domain.poc.db.PocService;
import com.agileboot.domain.poc.db.SearchPocDO;
import com.agileboot.domain.poc.dto.PocDTO;
import com.agileboot.domain.poc.model.PocModel;
import com.agileboot.domain.poc.model.PocModelFactory;
import com.agileboot.domain.poc.query.PocQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class PocApplicationService {

    private final PocService pocService;
    private final PocModelFactory pocModelFactory;

    public PageDTO<PocDTO> getPocList(PocQuery<SearchPocDO> query) {
        Page<SearchPocDO> pocPage = pocService.getPocList(query);
        List<PocDTO> pocDTOList = pocPage.getRecords().stream().map(PocDTO::new).collect(Collectors.toList());
        return new PageDTO<>(pocDTOList, pocPage.getTotal());
    }

    public PocDTO getPocInfo(Long pocId) {
        PocEntity byId = pocService.getById(pocId);
        return new PocDTO(byId);
    }

    public void addPoc(AddPocCommand command) {
        PocModel model = pocModelFactory.create();
        model.loadFromAddCommand(command);

        model.checkProjectUnique();

        model.insert();
    }

    public void updatePoc(UpdatePocCommand command) {
        PocModel model = pocModelFactory.loadById(command.getPocId());
        model.loadFromUpdateCommand(command);

        model.checkProjectUnique();
        model.checkFieldRelatedEntityExist();
        model.updateById();
    }

    public void deletePocs(BulkOperationCommand<Long> deleteCommand) {
//        for (Long id : command.getIds()) {
//            PocModel model = pocModelFactory.loadById(id);
//            model.deleteById();
//        }
        pocService.removeBatchByIds(deleteCommand.getIds());
    }


}
