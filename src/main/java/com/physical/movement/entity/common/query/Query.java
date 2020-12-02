package com.physical.movement.entity.common.query;

import java.io.Serializable;

public class Query implements Serializable {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
