package org.brandon.data.advent2022;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about monkeys and their items.
 * Used in the Advent of Code 2022: Day 11 challenge.
 */
@Data
public class Day11Model {

    private String name;
    private List<Long> startingItems;
    private String operationType;
    private String operationValue;
    private int test;
    private int trueResult;
    private int falseResult;
    private int itemsInspected;

    public Day11Model() {
        this.name = "Monkey -1";
        this.startingItems = new ArrayList<>();
        this.operationType = "N/A";
        this.operationValue = "N/A";
        this.test = -1;
        this.trueResult = -1;
        this.falseResult = -1;
        this.itemsInspected = 0;
    }

    public Day11Model(String name, List<Long> startingItems, String operationType, String operationValue, int test, int trueResult, int falseResult) {
        this.name = name;
        this.startingItems = startingItems;
        this.operationType = operationType;
        this.operationValue = operationValue;
        this.test = test;
        this.trueResult = trueResult;
        this.falseResult = falseResult;
        this.itemsInspected = 0;
    }

    public void updateItemsInspected() {
        this.itemsInspected += 1;
    }

}
