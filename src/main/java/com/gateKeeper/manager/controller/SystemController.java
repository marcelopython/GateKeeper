package com.gateKeeper.manager.controller;

import com.gateKeeper.manager.dto.SystemDTO;
import com.gateKeeper.manager.model.System;
import com.gateKeeper.manager.service.contract.SystemServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/systems")
public class SystemController {

    private final SystemServiceInterface systemService;

    public SystemController(SystemServiceInterface systemService) {
        this.systemService = systemService;
    }

    @GetMapping
    public ResponseEntity<List<System>> index(){
        List<System> systems = systemService.all();
        return ResponseEntity.ok().body(systems);
    }

    @PostMapping
    public ResponseEntity<SystemDTO> create(@Valid @RequestBody SystemDTO systemDTO) {

        SystemDTO newSystem = systemService.create(systemDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(systemDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newSystem);

    }



}
