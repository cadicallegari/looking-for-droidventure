package com.cadi.lookingfordroidventure.models;

/**
 * Created by cadi on 13/06/15.
 */
public class Stop {

    private Long id;
    private String name;
    private Long routeId;
    private Long sequence;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getSequence() {

        return sequence;
    }

    public void setSequence(Long sequence) {

        this.sequence = sequence;
    }

    public Long getRouteId() {

        return routeId;
    }

    public void setRouteId(Long routeId) {

        this.routeId = routeId;
    }
}