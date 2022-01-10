package com.hlxndr.jobsearchapplication.controller;

import com.hlxndr.jobsearchapplication.model.ClientApp;
import com.hlxndr.jobsearchapplication.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api-v1")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/clients")
    public UUID registerClient(@RequestBody ClientApp clientApp) {
       return clientService.registerClient(clientApp);
    }
}
