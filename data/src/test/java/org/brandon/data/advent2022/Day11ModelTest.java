package org.brandon.data.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class Day11ModelTest {

    private Day11Model day11Model;
    private String name;
    private List<Long> startingItems;
    private String operationType;
    private String operationValue;
    private int test;
    private int trueResult;
    private int falseResult;

    @BeforeEach
    void setup() {
        name = "Test Name";
        startingItems = new ArrayList<>();
        startingItems.add(1L);
        startingItems.add(2L);
        operationType = "Fake Operation";
        operationValue = "Fake Value";
        test = 1;
        trueResult = 2;
        falseResult = 3;
        day11Model = new Day11Model(name, startingItems, operationType, operationValue, test, trueResult, falseResult);
    }

    @Test
    void testGetSet() {
        day11Model = new Day11Model();
        day11Model.setName(name);
        day11Model.setStartingItems(startingItems);
        day11Model.setOperationType(operationType);
        day11Model.setOperationValue(operationValue);
        day11Model.setTest(test);
        day11Model.setTrueResult(trueResult);
        day11Model.setFalseResult(falseResult);

        assertEquals(name, day11Model.getName());
        assertEquals(1L, day11Model.getStartingItems().getFirst());
        assertEquals(operationType, day11Model.getOperationType());
        assertEquals(operationValue, day11Model.getOperationValue());
        assertEquals(test, day11Model.getTest());
        assertEquals(trueResult, day11Model.getTrueResult());
        assertEquals(falseResult, day11Model.getFalseResult());
    }

    @Test
    void testUpdateItemsInspected() {
        assertEquals(0, day11Model.getItemsInspected());
        day11Model.updateItemsInspected();
        assertEquals(1, day11Model.getItemsInspected());
    }

}
