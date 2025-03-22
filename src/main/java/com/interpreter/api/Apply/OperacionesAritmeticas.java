package com.interpreter.api.Apply;

import java.util.List;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispNumber;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionesAritmeticas.java
 * Descripción: implementa las operaciones aritmeticas basicas 
 */

public class OperacionesAritmeticas implements LispOperator {
    private LispExpressionFactory factory;
    private Environment contexto;

    /**
     * Constructor correspondiente
     * @param contexto el contexto donde se desarrollarán las operaciones
     */
    public OperacionesAritmeticas (Environment contexto) {
        this.contexto = contexto;
        this.factory = new LispExpressionFactory(contexto);
    }

    /**
     * Verifica que el simbolo ingresado como argumento sean los reservados de las operacioens aritméticas
     * @param simbolo el simbolo de la operacion aritmetica a realizar
     * @return verdadero si se soporta el operador, falso si no se soporta
     */
    @Override
    public boolean supports(String simbolo) {
        return (
            simbolo.equals("+") || 
            simbolo.equals("-") || 
            simbolo.equals("*") || 
            simbolo.equals("/")
        );
    }

    /**
     * Aplica una operación básica a dos operandos
     * @param simbolo El simbolo que representa la operacion aritmética a realizar
     * @param args Una lista de que contiene los operandos
     * @param contexto el entorno donde se evalúan las operaciones
     * @return el resultado de la operación aritmética como un lispnumber
     */
    @Override
    public LispExpression apply(String simbolo, List<LispExpression> args, Environment contexto) {
        if (args.size() < 2) {
            return this.factory.createAtom("ERROR: argumentos insuficientes");
        }

        LispExpression a_number = args.get(0).evaluate();
        if (!(a_number instanceof LispNumber)) {
            try {
                a_number = this.contexto.obtenerVariable(args.get(0).toString()).evaluate();
            } catch (Exception e) {
                return this.factory.createAtom("ERROR: los operandos deben ser numeros");
            }
        }

        if (!(a_number instanceof LispNumber)) {
            return this.factory.createAtom("ERROR: los operandos deben ser numeros");
        }

        LispExpression b_number = args.get(1).evaluate();
        if (!(b_number instanceof LispNumber)) {
            try {
                b_number = this.contexto.obtenerVariable(args.get(1).toString()).evaluate();
            } catch (Exception e) {
                return this.factory.createAtom("ERROR: los operandos deben ser numeros");
            }
        }

        if (!(b_number instanceof LispNumber)) {
            return this.factory.createAtom("ERROR: los operandos deben ser numeros");
        }

        double a = ((LispNumber) a_number).getValue();
        double b = ((LispNumber) b_number).getValue();
        
        if (simbolo.equals("+")) {
            return new LispNumber(a + b);
        }

        if (simbolo.equals("-")) {
            return new LispNumber(a - b);
        }

        if (simbolo.equals("*")) {
            return new LispNumber(a * b);
        }

        if (simbolo.equals("/")) {
            if (b == 0) {
                return this.factory.createAtom("ERROR: división por cero");
            }
            return new LispNumber(a / b);
        }

        return this.factory.createAtom("ERROR: operacion desconocida");
    }
}