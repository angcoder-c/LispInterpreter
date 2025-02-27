package com.interpreter.api.Expresion;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Angel chavez
 * Creación: 27/02/2025
 * última modificación: 27/02/2025
 * File Name: LispAtom.java
 * Descripción: LispExpression que representa un átomo
 */

public class LispAtom implements LispExpression {
    private String valor;

    /**
     * @param value un string que representa un átomo
     */
    public LispAtom(String valor) {
        this.valor = valor;
    }

    /**
     * @return un string con el tipo de dato del átomo
     * si es posible parsearlo, si no se considera un nombre de función (simbolo)
     */
    @Override
    public String getType() {
        
        if (
            valor.equalsIgnoreCase("true") || 
            valor.equalsIgnoreCase("false")
        ) {
            return "Boolean";
        } 

        try {
            Double.parseDouble(valor);
            return "Number";
        }  catch (Exception e) {}

        return "Symbol";
    }

    @Override
    public LispExpression evaluate() {
        return this;
    }

    /**
     * @return el string que represeta al atomo
     */
    @Override
    public String toString() {
        return valor;
    }
}
