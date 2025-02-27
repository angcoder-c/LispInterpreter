package com.interpreter.api;

import java.util.List;

public class Parser {
    /**
     * @author Angel chavez
     * @param tokens una lista <List> que reiva si una expresion es un átomo (numero string, etc)
     * @return true si es un átomo, false si lo és
     */
    public static boolean isAtom(List<String> tokens) {
        if (
            tokens.size() == 1 && 
            !tokens.get(0).equals("(") && 
            !tokens.get(0).equals(")")
        ) {
            return true;
        }
        return false;
    }
}
