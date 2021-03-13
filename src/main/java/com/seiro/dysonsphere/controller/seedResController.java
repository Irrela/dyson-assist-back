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
        Query query = new Query();
        if(!request.getStarType().equals("")) {
            query.addCriteria(Criteria.where("starType").is(request.getStarType()));
        }
        if(request.getPlanets().size() != 0) {
            query.addCriteria(Criteria.where("planets").all(request.getPlanets()));
        }
        if(request.getRareResources().size() != 0) {
            query.addCriteria(Criteria.where("rareResources").all(request.getRareResources()));
        }

        StringBuilder str = new StringBuilder();
        switch (request.getSortType()) {
            case "light_effic":
                str.append("lightEffic");
                break;
            case "wind_effic":
                str.append("windEffic");
            case "rare_num":
                str.append("rareNum");
            default:
                break;
        }

        if(request.getAscending() == 1) {
            query.with(new Sort(Sort.Direction.ASC, new String(str)));
        } else {
            query.with(new Sort(Sort.Direction.DESC, new String(str)));
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
