package com.seiro.dysonsphere.service;

import com.seiro.dysonsphere.dao.SeedDAO;
import com.seiro.dysonsphere.pojo.Request;
import com.seiro.dysonsphere.pojo.Seed;
import com.seiro.dysonsphere.redis.RedisService;
import com.seiro.dysonsphere.utils.CastUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeedService {
    @Autowired
    SeedDAO seedDAO;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    RedisService redisService;

    public List<Seed> list() {
        List<Seed> seeds;
        String key = "seedList";
        Object seedCache = redisService.get(key);

        if(seedCache == null) {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            seeds = seedDAO.findAll(sort);
            redisService.set(key, seeds);
        } else {
            seeds = CastUtils.objectConvertToList(seedCache, Seed.class);
        }

        return seeds;
    }

    public List<Seed> multiQuery(Request request) {
        List<Seed> seeds;
        Object seedCache;
        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder sortType = new StringBuilder();
        Query query = new Query();

        keyBuilder.append("Query"); // 删除查询结果缓存所用标志pattern
        if(!request.getStarType().equals("")) {
            query.addCriteria(Criteria.where("starType").is(request.getStarType()));
            keyBuilder.append(request.getStarType());
        }
        if(request.getPlanets().size() != 0) {
            query.addCriteria(Criteria.where("planets").all(request.getPlanets()));
            keyBuilder.append(String.join("", request.getPlanets()));
        }
        if(request.getRareResources().size() != 0) {
            query.addCriteria(Criteria.where("rareResources").all(request.getRareResources()));
            keyBuilder.append(String.join("", request.getRareResources()));
        }

        keyBuilder.append(request.getSortType());
        keyBuilder.append(request.getAscending());

        String key = new String(keyBuilder);
        seedCache = redisService.get(key);
        if(seedCache == null) {
            switch (request.getSortType()) {
                case "light_effic":
                    sortType.append("lightEffic");
                    break;
                case "wind_effic":
                    sortType.append("windEffic");
                case "rare_num":
                    sortType.append("rareNum");
                default:
                    break;
            }

            if(request.getAscending() == 1) {
                query.with(new Sort(Sort.Direction.ASC, new String(sortType)));
            } else {
                query.with(new Sort(Sort.Direction.DESC, new String(sortType)));
            }

            if(mongoTemplate.find(query, Seed.class) == null) {
                seeds = new ArrayList<>();
            } else {
                seeds = mongoTemplate.find(query, Seed.class);
            }

            redisService.set(key, seeds);
        } else {
            seeds = CastUtils.objectConvertToList(seedCache, Seed.class);
        }

        return seeds;
    }

    public void addOrUpdate(Seed seed) {
        redisService.delete(redisService.getKeysByPattern("Query"));
        redisService.delete("seedList");
        seedDAO.insert(seed);

        // 在mongo存入之后等待一会儿再删除一次redis的缓存
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redisService.delete(redisService.getKeysByPattern("Query"));
        redisService.delete("seedList");
    }

    public void deleteById(String id) {
        redisService.delete(redisService.getKeysByPattern("Query"));
        redisService.delete("seedList");
        seedDAO.deleteById(id);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redisService.delete(redisService.getKeysByPattern("Query"));
        redisService.delete("seedList");
    }
}
