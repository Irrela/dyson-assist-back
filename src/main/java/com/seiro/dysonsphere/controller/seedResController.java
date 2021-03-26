package com.seiro.dysonsphere.controller;

import com.seiro.dysonsphere.pojo.Request;
import com.seiro.dysonsphere.pojo.Seed;
import com.seiro.dysonsphere.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class seedResController {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    SeedService seedService;

    @CrossOrigin
    @GetMapping(value = "/api/seeds")
    public List<Seed> list() throws Exception {
        return seedService.list();
    }

    @CrossOrigin
    @PostMapping("/api/filterSeeds")
    public List<Seed> list(@RequestBody Request request) throws Exception {
        return seedService.multiQuery(request);
    }

    @CrossOrigin
    @PostMapping("/api/seeds")
    public Seed addOrUpdate(@RequestBody Seed seed) throws Exception {
        seedService.addOrUpdate(seed);
        return seed;
    }

}
