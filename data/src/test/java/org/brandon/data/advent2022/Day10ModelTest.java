package org.brandon.data.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class Day10ModelTest {

    private Day10Model day10Model;
    private String action;
    private Integer number;


    @BeforeEach
    void setup() {
        action = "action";
        number = 1;
        day10Model = new Day10Model(action, number);
    }

    @Test
    void testGetSet() {
        action = "new action";
        number = 10;
        day10Model.setAction(action);
        day10Model.setNumber(number);
        assertEquals(action, day10Model.getAction());
        assertEquals(number, day10Model.getNumber());

        number = null;
        day10Model.setNumber(null);
        assertEquals(0, day10Model.getNumber());
    }

    @Test
    void testActionConstructor() {
        day10Model = new Day10Model(action);
        assertEquals(action, day10Model.getAction());
        assertEquals(0, day10Model.getNumber());
    }

}
