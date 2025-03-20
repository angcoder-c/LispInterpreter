package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionLlamadaFuncion.java
 * Descripción: realiza la invocacion de las funciones
 * definidas por el usuario
 */

public class OperacionLlamadaFuncion implements LispOperator {
    private LispExpressionFactory factory;

    public OperacionLlamadaFuncion (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    @Override
    public boolean supports(String simbolo) {
        return true;
    }

    @Override
    public LispExpression apply(
        String simbolo, 
        List<LispExpression> args, 
        Environment contexto
    ) {
        if (!contexto.existeFuncion(simbolo)) {
            return this.factory.createAtom(
                "ERROR: función no definida: " + simbolo
            );
        }

        LispFunction funcion = contexto.obtenerFuncion(simbolo);

        // evaluar los argumentos y pasarlos a la función
        List<LispExpression> argumentosEvaluados = new ArrayList<>();
        for (LispExpression arg : args) {
            argumentosEvaluados.add(arg.evaluate());
        }

        return funcion.call(argumentosEvaluados, contexto);
    }
}
