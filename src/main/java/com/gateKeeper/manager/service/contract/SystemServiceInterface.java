package com.gateKeeper.manager.service.contract;

import com.gateKeeper.manager.dto.SystemDTO;
import com.gateKeeper.manager.model.System;

import java.util.List;

public interface SystemServiceInterface {
    public SystemDTO create(SystemDTO systemDTO);
    public List<System> all();

}
