package com.interpreter.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;

public class Parser {
    private LispExpressionFactory factory;

    public Parser (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    /**
     * @author Angel chavez
     * @param tokens una lista <List> que reiva si una expresion es un átomo (numero string, etc)
     * @return true si es un átomo, false si lo és
     */
    public static boolean isAtom(String lisp) {
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.read_str(lisp);
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

        if (lisp == null || lisp.trim().isEmpty()) {
            return this.factory.createAtom("ERROR: expresión vacia");
        }

        Lexer lexer = new Lexer();

        if (isAtom(lisp)){
            return parseExpression(Arrays.asList(lisp), new int[] {0});
        }

        if (!lexer.verificarPerentesis(lisp) && !isAtom(lisp)) {
            return this.factory.createAtom("ERROR: error de sintaxis");
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
        // obtiene un token
        String token = tokens.get(index[0]++);

        // si es una lista
        if (token.equals("(")) {
            List<LispExpression> elementos = new ArrayList<>();
    
            while (
                index[0] < tokens.size() && 
                !tokens.get(index[0]).equals(")")
            ) {
                // si no está fuera de rango y el ultimo token no es )
                LispExpression expr = parseExpression(tokens, index);
                elementos.add(expr);
            }

            index[0]++; // se salta )
            return this.factory.createList(elementos);
        }

        else {
            return this.factory.createAtom(token);
        }
    }
    
}
