package com.physical.movement.model;

import java.util.HashMap;
import java.util.Map;

public enum SportsType {
    ALL((byte) 0, "全部"),
    BASKETBALL((byte) 1, "篮球"),
    FOOTBALL((byte) 2, "足球"),
    RUN((byte) 3, "跑步"),
    SWIM((byte) 4, "游泳"),
    OTHERS((byte) 5, "其他");

    public final Byte val;
    public final String name;

    public final static Map<Byte, SportsType> STATUS_MAP = init();

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    SportsType(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    private static Map<Byte, SportsType> init() {
        Map<Byte, SportsType> map = new HashMap<>();
        for (SportsType status : SportsType.values()) {
            map.put(status.val, status);
        }
        return map;
    }
}
