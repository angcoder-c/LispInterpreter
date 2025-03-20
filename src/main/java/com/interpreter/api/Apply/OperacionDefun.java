package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispList;
import com.interpreter.api.Expresion.LispFunction;

import java.util.ArrayList;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionDefun.java
 * Descripción: implementa la funcion defun de lisp
 * para definir una nueva función
 */

public class OperacionDefun implements LispOperator {
    private LispExpressionFactory factory;

    public OperacionDefun (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    @Override
    public boolean supports(String symbol) {
        return symbol.equals("defun");
    }

    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.size() < 3) {
            return this.factory.createAtom(
                "ERROR: defun necesita nombre, parametros y cuerpo"
            );
        }

        String nombreFuncion = args.get(0).toString();
        LispExpression parametrosExpr = args.get(1);
        LispExpression cuerpo = args.get(2);

        if (!(parametrosExpr instanceof LispList)) {
            return this.factory.createAtom(
                "ERROR: los parametros deben ser una lista"
            );
        }

        LispList lista = (LispList) parametrosExpr;
        List<String> parametros = new ArrayList<>();
        for (Object elem : lista.getElementos()) {
            parametros.add(elem.toString());
        }

        contexto.definirFuncion(nombreFuncion, new LispFunction(parametros, cuerpo, contexto));
        return this.factory.createAtom(nombreFuncion);
    }
}
