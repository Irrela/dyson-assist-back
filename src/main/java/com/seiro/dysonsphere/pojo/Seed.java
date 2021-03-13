package com.seiro.dysonsphere.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Document(collection = "seed")
public class Seed {
    @Id
    String id;

    String seedId;
    String starType;
    String starName;
    List<String> planets;
    Integer windEffic;
    Integer lightEffic;
    Boolean isLightEternal;
    List<String> rareResources;
    Integer rareNum;

    public Seed(String id, String seedId, String starType, String starName, List<String> planets, Integer windEffic, Integer lightEffic, Boolean isLightEternal, List<String> rareResources, Integer rareNum) {
        this.id = id;
        this.seedId = seedId;
        this.starType = starType;
        this.starName = starName;
        this.planets = planets;
        this.windEffic = windEffic;
        this.lightEffic = lightEffic;
        this.isLightEternal = isLightEternal;
        this.rareResources = rareResources;
        this.rareNum = rareResources.size();
    }

    public String getSeedId() {
        return seedId;
    }

    public void setSeedId(String seedId) {
        this.seedId = seedId;
    }

    public String getId() {
        return seedId;
    }

    public void setId(String seedId) {
        this.seedId = seedId;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public Integer getLightEffic() {
        return lightEffic;
    }

    public void setLightEffic(Integer lightEffic) {
        this.lightEffic = lightEffic;
    }

    public Boolean getLightEternal() {
        return isLightEternal;
    }

    public void setLightEternal(Boolean lightEternal) {
        isLightEternal = lightEternal;
    }

    public List<String> getRareResources() {
        return rareResources;
    }

    public void setRareResources(List<String> rareResources) {
        this.rareResources = rareResources;
    }

    public Integer getWindEffic() {
        return windEffic;
    }

    public void setWindEffic(Integer windEffic) {
        this.windEffic = windEffic;
    }

    public Integer getRareNum() {
        return rareNum;
    }

    public void setRareNum(Integer rareNum) {
        this.rareNum = rareNum;
    }

}
