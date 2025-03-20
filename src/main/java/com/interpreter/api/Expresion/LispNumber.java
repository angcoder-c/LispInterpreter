package com.interpreter.api.Expresion;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcela Castillo
 * Creación: 18/03/2025
 * última modificación: 19/02/2025
 * File Name: Environment.java
 * Descripción: Contexto del interprete
 */

public class LispNumber implements LispExpression {
    private double value;

    // constructor por valor de string
    public LispNumber(String valor) {
        this.value = Double.parseDouble(valor);
    }

    // constructor por valor decimal
    public LispNumber(double value) {
        this.value = value;
    }

    /**
     * @return valor decimal del numero
     */
    public double getValue() {
        return value;
    }

    /**
     * @return devuelve el tipo de dato Number
     */
    @Override
    public String getType() {
        return "Number";
    }

    @Override
    // se evalua por si mismo
    public LispExpression evaluate() {
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}