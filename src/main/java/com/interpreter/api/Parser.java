package com.interpreter.api;

import java.util.ArrayList;
import java.util.List;

import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;

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

    /**
     * método principal del parser.
     * @param list String con el código de lisp a parsear
     * @return LispExpression que representa a los elementos de código lisp 
     */
    public LispExpression parse(String lisp) {
        Lexer lexer = new Lexer();

        if (!lexer.verificarPerentesis(lisp)) {
            return LispExpressionFactory.createAtom("ERROR: lista no cerrada");
        }

        List<String> tokens = lexer.read_str(lisp);
        int[] index = {0};

        return parseExpression(tokens, index);
    }

    /**
     * metodo para recorrer la lista de tokens
     * recursivo
     */
    private LispExpression parseExpression(List<String> tokens, int[] index) {
        
    }
    
}
