package com.interpreter.api.Apply;

import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Environment;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionesSistema.java
 * Descripción: implementa operaciones del sistema
 */

public class OperacionesSistema implements LispOperator {

    private LispExpressionFactory factory;

    public OperacionesSistema (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    @Override
    public boolean supports(String simbolo) {
        return simbolo.equals("exit");
    }

    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        return this.factory.createAtom("Bye");
    }
}
