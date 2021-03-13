package com.seiro.dysonsphere.pojo;

import java.util.List;

public class Request {

    String starType;
    List<String> planets;
    List<String> rareResources;
    String sortType;
    Integer ascending;

    public Integer getAscending() {
        return ascending;
    }

    public void setAscending(Integer ascending) {
        this.ascending = ascending;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public List<String> getRareResources() {
        return rareResources;
    }

    public void setRareResources(List<String> rareResources) {
        this.rareResources = rareResources;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
