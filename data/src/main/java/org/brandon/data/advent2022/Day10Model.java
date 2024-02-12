package org.brandon.data.advent2022;

import lombok.Data;

/**
 * Contains the operation and an optional value for the operation taking place.
 * Used in the Advent of Code 2022: Day 10 challenge.
 */
@Data
public class Day10Model {

    private String action;
    private Integer number;

    public Day10Model(String action, Integer number) {
        this.action = action;
        this.number = number;
    }

    public Day10Model(String action) {
        this.action = action;
        this.number = null;
    }

    public Integer getNumber() {
        if (this.number == null) {
            return 0;
        }
        return this.number;
    }
}
