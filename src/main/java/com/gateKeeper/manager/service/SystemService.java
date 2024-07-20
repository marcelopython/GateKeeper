package com.gateKeeper.manager.service;

import com.gateKeeper.manager.dto.SystemDTO;
import com.gateKeeper.manager.model.System;
import com.gateKeeper.manager.repository.SystemRepository;
import com.gateKeeper.manager.service.contract.SystemServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class SystemService implements SystemServiceInterface {

    private final SystemRepository systemRepository;

    public SystemService(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    public List<System> all(){
         return systemRepository.findAll();
    }

    public SystemDTO create(SystemDTO systemDTO) {
        systemDTO.setCompanyId(Auth.getUser().getCompanyId());
        System system = systemRepository.save(systemDTO.toEntity());
        java.lang.System.out.println(system);
        return system.toDTO();
    }

}
