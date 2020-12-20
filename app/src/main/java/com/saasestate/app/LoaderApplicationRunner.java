package com.saasestate.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saasestate.app.component.parser.Parser;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.entity.EstateData;
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
public class LoaderApplicationRunner implements ApplicationRunner {

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
        List<EstateData> data = new ArrayList<>();

        var index = parser.getIndex();

        for (var link : index.getLinks()) {
            for (var item : link) {
                var errors = validator.validate(item);

                if (!errors.isEmpty()) {
                    for (ConstraintViolation<Item> violation : errors) {
                        log.warn(String.format("ID %s: %s %s", item.objectId, violation.getPropertyPath(), violation.getMessage()));
                    }
                } else {
                    data.add(mapper.toEntityData(item));
                    this.flush(data, false);
                }
            }
            this.flush(data, true);
        }

        updateEntitiesMeta();

    }

    public void updateEntitiesMeta() throws JsonProcessingException {
        EstateData data = null;
        while ((data = estateService.getNextToUpdate()) != null) {
            estateService.upsert(data);
        }
    }

    /**
     *
     * @param data
     * @param force
     */
    private void flush(List<EstateData> data, Boolean force) {
        if (force || data.size() >= BATCH_SIZE) {
            estateService.batchDataInsert(data);
        }
    }

}
