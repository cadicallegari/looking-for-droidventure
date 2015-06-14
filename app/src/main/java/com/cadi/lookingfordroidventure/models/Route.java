package com.cadi.lookingfordroidventure.models;

import java.util.Date;

/**
 * Created by cadi on 13/06/15.
 */
public class Route {

    private Long id;
    private Long agencyId;
    private String longName;
    private String shortName;
    private Date lastModifiedDate;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getShortName() {

        return shortName;
    }

    public void setShortName(String shortName) {

        this.shortName = shortName;
    }

    public String getLongName() {

        return longName;
    }

    public void setLongName(String longName) {

        this.longName = longName;
    }

    public Date getLastModifiedDate() {

        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getAgencyId() {

        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }
}
