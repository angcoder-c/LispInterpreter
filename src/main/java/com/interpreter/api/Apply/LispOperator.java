package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispExpression;

import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: LispOperator.java
 * Descripción: Interfazz definida para aplicar las funciones de un operador
 */

public interface LispOperator {
    // operador soportada por el interprete
    boolean supports(String simbolo);

    // aplicar la funcion del operador
    LispExpression apply(
        String simbolo, 
        List<LispExpression> args, 
        Environment contexto
    );
}