package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;

import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionQuote.java
 * Descripción: implementa el operador quote 
 */

public class OperacionQuote implements LispOperator {
    private LispExpressionFactory factory;

    /**
     * Constructor correspondiente
     * @param contexto contexto donde se realiza quote
     */
    public OperacionQuote (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    /**
     * Método para verificar el operador reservado para quote
     * @param simbolo el símbolo ingresado para hacer un quote
     * @return verdadero si el simbolo ingresado es el reservado para quote ("quote") o false si es otro simbolo
     */
    @Override
    public boolean supports(String simbolo) {
        return simbolo.toLowerCase().equals("quote");
    }

    /**
     * Lleva a cabo la operación quote (devolver el argumento sin evaluar)
     * @param simbolo el simbolo que representa la operacion (no utilizado)
     * @param args la lista de expresiones con el argumento a devolver sin evaluarlas
     * @param contexto entorno en el que se utiliza la expresión (no utilizado)
     * @return el argumento de la expresión sin evaluar, error si args está vacío.
     */
    @Override
    public LispExpression apply(String simbolo, List<LispExpression> args, Environment contexto) {
        if (args.isEmpty()) {
            return this.factory.createAtom(
                "ERROR: quote necesita un argumento"
            );
        }
        return args.get(0);
    }
}