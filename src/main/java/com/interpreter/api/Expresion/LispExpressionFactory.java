package com.interpreter.api.Expresion;

import java.util.List;

import com.interpreter.api.Environment;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author:Marcela Castillo
 * Creación: 13/03/2025
 * última modificación: 18/02/2025
 * File Name: Environment.java
 * Descripción: Clase creadora de LispExpressions
 */

public class LispExpressionFactory {
    private Environment contexto;

    public LispExpressionFactory (Environment contexto){
        this.contexto = contexto;
    }

    public LispExpression createAtom(String valor) {
        return new LispAtom(valor, this.contexto);
    }

    public LispExpression createList(List<LispExpression> elementos) {
        return new LispList(elementos, this.contexto);
    }
}
