package com.gateKeeper.manager.main;

import com.gateKeeper.manager.controller.SystemController;
import com.gateKeeper.manager.repository.SystemRepository;
import com.gateKeeper.manager.service.SystemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemServiceConfig {

    @Bean
    SystemController systemController(SystemService systemService){
        return new SystemController(systemService);
    }

    @Bean
    SystemService systemService(SystemRepository systemRepository){
        return new SystemService(systemRepository);
    }

}
