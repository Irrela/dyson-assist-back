package com.seiro.dysonsphere.controller;

import com.seiro.dysonsphere.pojo.Seed;
import com.seiro.dysonsphere.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Seed> list(@RequestBody Seed seed) throws Exception {
        Query query = new Query();
        if(!seed.getStarType().equals("")) {
            query.addCriteria(Criteria.where("starType").is(seed.getStarType()));
        }
        if(seed.getPlanets().size() != 0) {
            query.addCriteria(Criteria.where("planets").all(seed.getPlanets()));
        }
        if(seed.getRareResources().size() != 0) {
            query.addCriteria(Criteria.where("rareResources").all(seed.getRareResources()));
        }

        if(mongoTemplate.find(query, Seed.class) == null) {
            return new ArrayList<Seed>();
        }
        return mongoTemplate.find(query, Seed.class);
    }

    @CrossOrigin
    @PostMapping("/api/seeds")
    public Seed addOrUpdate(@RequestBody Seed seed) throws Exception {
        seedService.addOrUpdate(seed);
        return seed;
    }


}
