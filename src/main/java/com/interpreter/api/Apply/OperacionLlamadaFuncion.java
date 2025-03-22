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
    private Environment contexto;

    /**
     * Constructor correspondiente
     * @param contexto entorno donde se llama a la función
     */
    public OperacionLlamadaFuncion (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
        this.contexto = contexto;
    }

    /**
     * método para cumplir con el contrato de la interface
     */
    @Override
    public boolean supports(String simbolo) {
        return true;
    }

    /**
     * Evalua una expresión que puede ser una variable o una llamada a una funcion
     * Si es una variable, se devuelve su valor evaluado. En caso de ser una función, evalúa los argumentos y llama a la función.
     * @param simbolo el simbolo que representa la función a evaluar o variable
     * @param args lista de expresiones con los argumentos para la función
     * @param contexto entorno en el que se evalúan las expresiones
     * @return la llamada a la función o el resultado de evaluar la variable
     */
    @Override
    public LispExpression apply(
        String simbolo, 
        List<LispExpression> args, 
        Environment contexto
    ) {
        if (this.contexto.existeVariable(simbolo)) {
            LispExpression variable = this.contexto.obtenerVariable(simbolo);
            return variable.evaluate();
        }
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
