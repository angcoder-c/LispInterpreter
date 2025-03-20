package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispNumber;

import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionIf.java
 * Descripción: implementa un condicional de lisp
 */

public class OperacionIf implements LispOperator {
    private LispExpressionFactory factory;
    private Environment contexto;

    public OperacionIf(Environment contexto) {
        this.contexto = contexto;
        this.factory = new LispExpressionFactory(contexto);
    }

    @Override
    public boolean supports(String symbol) {
        return symbol.toLowerCase().equals("if");
    }

    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.size() != 3) {
            return factory.createAtom("ERROR: if necesita 3 argumentos (condición, valor-si-true, valor-si-false)");
        }

        LispExpression condicion = args.get(0).evaluate();

        if (condicion instanceof LispAtom) {
            String valorCond = condicion.toString();
            
            if (valorCond.equalsIgnoreCase("nil") || valorCond.equalsIgnoreCase("false") || valorCond.equals("0")) {
                // false = devolver el tercer argumento
                return args.get(2).evaluate();
            } else {
                // true = devolver el segundo argumento
                return args.get(1).evaluate();
            }
        } else if (condicion instanceof LispNumber) {
            double valor = ((LispNumber) condicion).getValue();
            if (valor == 0) {
                // false
                return args.get(2).evaluate();
            } else {
                // true
                return args.get(1).evaluate();
            }
        }

        return args.get(1).evaluate();
    }
}