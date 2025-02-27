package com.interpreter.api;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ReadTest {
    @Test
    public void verificarPerentesisTest() {
        Read read = new Read();
        boolean valid = read.verificarPerentesis("(+ (+ 1 1) (+ 1 1))");
        boolean invalid = read.verificarPerentesis("(+ (+ 1 1 (+ 1 1))");
        
        assertTrue(valid);
        assertFalse(invalid);
    }

    @Test
    public void separarStringTest () {
        Read read = new Read();
        List<String> valid = read.read_str("(+ (+ 1 1) (+ 1 1))");

        assertEquals(valid, List.of("(", "+", "(", "+", "1", "1", ")", "(", "+", "1", "1", ")", ")"));
    }
    
}
