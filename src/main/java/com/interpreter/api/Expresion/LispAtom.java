package com.interpreter.api.Expresion;

import com.interpreter.api.Environment;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcela Castillo
 * Creación: 27/02/2025
 * última modificación: 27/02/2025
 * File Name: LispAtom.java
 * Descripción: LispExpression que representa un átomo
 */

public class LispAtom implements LispExpression {
    private String valor;
    private Environment contexto;

    /**
     * @param value un string que representa un átomo
     */
    public LispAtom(String valor, Environment contexto) {
        this.valor = valor;
        this.contexto = contexto;
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
        if (getType().equals("Number")) {
            return new LispNumber(valor);
        }

        if (this.contexto.existeVariable(this.valor)) {
            return contexto.obtenerVariable(this.valor);
        }

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
