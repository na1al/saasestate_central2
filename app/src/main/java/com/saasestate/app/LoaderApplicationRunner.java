package com.saasestate.app;

import com.saasestate.app.component.parser.Parser;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.entity.Source;
import com.saasestate.app.component.parser.dto.Item;
import com.saasestate.app.mappers.EstateMapper;
import com.saasestate.app.service.EstateService;
import com.saasestate.app.service.SourceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class LoaderApplicationRunner implements ApplicationRunner  {

    public static final int BATCH_SIZE = 1000;

    private SourceService sourceService;
    private EstateService estateService;
    private EstateMapper mapper;
    private Validator validator;

    @Autowired
    public LoaderApplicationRunner(
            SourceService sourceService,
            EstateService estateService,
            EstateMapper mapper,
            Validator validator
    ) {
        this.sourceService = sourceService;
        this.estateService = estateService;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

            Source source = sourceService.getNextSource();
            Parser parser = new Parser(source);
            List<Estate> estates = new ArrayList<>();

            var index = parser.getIndex();

            for (var link : index.getLinks()) {
                for (var item : link) {
                    var errors = validator.validate(item);

                    if (!errors.isEmpty()) {
                        for (ConstraintViolation<Item> violation : errors) {
                            log.warn(String.format("ID %s: %s %s", item.objectId, violation.getPropertyPath(), violation.getMessage()));
                        }
                    } else {
                        estates.add(mapper.toEntity(item));
                        this.flush(estates, false);
                    }
                }
                this.flush(estates, true);
            }

            Estate estate = estateService.getNextToUpdate().orElse(null);

            estateService.updateMetaData(estate);

    }

    /**
     * @param estates
     * @param force
     */
    private void flush(List<Estate> estates, Boolean force) {
        if (force || estates.size() >= BATCH_SIZE) {
            estateService.batchInsert(estates);
        }
    }

}
