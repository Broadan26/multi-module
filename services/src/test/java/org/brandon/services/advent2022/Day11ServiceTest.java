package org.brandon.services.advent2022;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.brandon.data.advent2022.Day11Model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class Day11ServiceTest {

    @Inject
    Day11Service day11Service;

    private Day11Model day11Model;

    @BeforeAll
    static void setup() {

    }

    @BeforeEach
    void setupEach() {
        day11Model = new Day11Model();
    }

    @Test
    void testGenerateStartingList() {
        String itemLine = "Starting items: 1, 2, 3, 5";
        day11Service.generateStartingList(day11Model, itemLine);
        List<Long> startingItems = day11Model.getStartingItems();
        assertEquals(4, startingItems.size());
        assertEquals(1L, startingItems.getFirst());
        assertEquals(5L, startingItems.getLast());
    }

    @Test
    void testGenerateOperation() throws IOException {
        // Addition Case
        String additionLine = "Operation: new = old + 2";
        day11Service.generateOperation(day11Model, additionLine);
        assertEquals("ADD", day11Model.getOperationType());
        assertEquals("2", day11Model.getOperationValue());

        // Subtraction Case
        String subtractionLine = "Operation: new = old - 3";
        day11Service.generateOperation(day11Model, subtractionLine);
        assertEquals("SUBTRACT", day11Model.getOperationType());
        assertEquals("3", day11Model.getOperationValue());

        // Multiplication Case
        String multiplicationLine = "Operation: new = old * 4";
        day11Service.generateOperation(day11Model, multiplicationLine);
        assertEquals("MULTIPLY", day11Model.getOperationType());
        assertEquals("4", day11Model.getOperationValue());

        // Division Case
        String divisionLine = "Operation: new = old / 5";
        day11Service.generateOperation(day11Model, divisionLine);
        assertEquals("DIVIDE", day11Model.getOperationType());
        assertEquals("5", day11Model.getOperationValue());

        // Bad Case
        String noSymbolLine = "No symbol found";
        Throwable exception = assertThrows(IOException.class, () ->{
            day11Service.generateOperation(day11Model, noSymbolLine);
        });
        assertEquals("Invalid file provided", exception.getMessage());
    }

    @Test
    void testGenerateTest() {
        // Basic test
        String testLine = "Test: divisible by 23";
        day11Service.generateTest(day11Model, testLine);
        assertEquals(23, day11Model.getTest());

        // Weird test
        testLine = "49Test: divisible by ";
        day11Service.generateTest(day11Model, testLine);
        assertEquals(49, day11Model.getTest());
    }

    @Test
    void testGenerateTrueResult() {
        String trueLine = "Anything canGeaux here9";
        day11Service.generateTrueResult(day11Model, trueLine);
        assertEquals(9, day11Model.getTrueResult());
    }

    @Test
    void testGenerateFalseResult() {
        String falseLine = "False line 3";
        day11Service.generateFalseResult(day11Model, falseLine);
        assertEquals(3, day11Model.getFalseResult());
    }

}
