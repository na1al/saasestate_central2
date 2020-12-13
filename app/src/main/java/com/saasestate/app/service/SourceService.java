package com.saasestate.app.service;

import com.saasestate.app.entity.Source;
import com.saasestate.app.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService {

    protected SourceRepository sourceRepository;

    @Autowired
    public SourceService(SourceRepository sourceRepository){
        this.sourceRepository = sourceRepository;
    }

    /**
     *
     * @return
     */
    public Source getNextSource(){
        return sourceRepository.findById(1).orElse(null);
    }

}
