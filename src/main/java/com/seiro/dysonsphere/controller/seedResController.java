package com.seiro.dysonsphere.controller;

import com.seiro.dysonsphere.pojo.Seed;
import com.seiro.dysonsphere.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class seedResController {
    @Autowired
    SeedService seedService;

    @CrossOrigin
    @GetMapping(value = "/api/seeds")
    public List<Seed> list() throws Exception {
        return seedService.list();
    }

    @CrossOrigin
    @PostMapping("/api/seeds")
    public Seed addOrUpdate(@RequestBody Seed seed) throws Exception {
        seedService.addOrUpdate(seed);
        return seed;
    }
}
