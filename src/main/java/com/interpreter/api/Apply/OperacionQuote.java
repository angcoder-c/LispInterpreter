package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;

import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionQuote.java
 * Descripción: implementa el operador quote 
 */

public class OperacionQuote implements LispOperator {
    private LispExpressionFactory factory;

    public OperacionQuote (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    @Override
    public boolean supports(String simbolo) {
        return simbolo.toLowerCase().equals("quote");
    }

    @Override
    public LispExpression apply(String simbolo, List<LispExpression> args, Environment contexto) {
        if (args.isEmpty()) {
            return this.factory.createAtom(
                "ERROR: quote necesita un argumento"
            );
        }
        return args.get(0);
    }
}