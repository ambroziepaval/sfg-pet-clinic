package com.ambroziepaval.sfgpetclinic.model;

import java.io.Serializable;

/**
 * Created by Ambrozie on 08/10/2018
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}