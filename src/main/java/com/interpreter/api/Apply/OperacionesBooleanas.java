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
 * File Name: OperacionesBooleanas.java
 * Descripción: implementa comparadores booleanos basicos
 */

public class OperacionesBooleanas implements LispOperator {
    private LispExpressionFactory factory;

    /**
     * Constructor correspondiente
     * @param contexto entorno dondese desarrollaran las operaciones
     */
    public OperacionesBooleanas(Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }
    /**
     * Verifica que el simbolo ingresado sean los reservados para las operaciones booleanas
     * @param simbolo el simbolo que representa la operacion booleana a realizar
     * @return true si el simbolo es soportado, false si no
     */
    @Override
    public boolean supports(String simbolo) {
        return (
            simbolo.equals("=") || 
            simbolo.equals("<") || 
            simbolo.equals(">") || 
            simbolo.equals("<=") || 
            simbolo.equals(">=") || 
            simbolo.equals("/=")
        );
    }
    /**
     * Evalúa los distintos argumentos para realzar las operaciones booleanas correspondientes
     * @param simbolo el comparador lógico a realizar
     * @param args una lista de expresiones con los dos operandos a comparar
     * @param contexto el entorno donde se evaluan las expresiones
     * @return Un átomo que contiene true o false según las comparaciones
     */
    @Override
    public LispExpression apply(String simbolo, List<LispExpression> args, Environment contexto) {
        if (args.size() != 2) {
            return factory.createAtom("ERROR: la operación booleana requiere exactamente 2 argumentos");
        }

        // ambas clausulas
        LispExpression left = args.get(0).evaluate();
        LispExpression right = args.get(1).evaluate();

        if (!(left instanceof LispNumber) || !(right instanceof LispNumber)) {
            return factory.createAtom("ERROR: los operandos deben ser números");
        }

        double a = ((LispNumber) left).getValue();
        double b = ((LispNumber) right).getValue();

        boolean resultado=false;

        // comparacion
        if (simbolo.equals("=")) {
            resultado = a == b;
        }
        if (simbolo.equals("<")) {
            resultado = a < b;
        }
        if (simbolo.equals(">")) {
            resultado = a > b;
        }
        if (simbolo.equals("<=")) {
            resultado = a <= b;
        }
        if (simbolo.equals(">=")) {
            resultado = a >= b;
        }
        if (simbolo.equals("/=")) {
            resultado = a != b;
        }

        return factory.createAtom(resultado ? "true" : "false");
    }
}