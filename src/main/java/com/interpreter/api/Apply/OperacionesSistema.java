package com.interpreter.api.Apply;

import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Environment;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionesSistema.java
 * Descripción: implementa operaciones del sistema
 */

public class OperacionesSistema implements LispOperator {

    private LispExpressionFactory factory;

    /**
     * Constructor correspondiente
     * @param contexto entorno donde se desarrolla la operación
     */

    public OperacionesSistema (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }

    /**
     * Verifica que el simbolo ingresado sea el correspondiente al usado para salir del programa
     * @param simbolo simbolo para comproobar que sea el reservado para salir (exit)
     * @return verdadero si el símbolo es "exit"
     */
    @Override
    public boolean supports(String simbolo) {
        return simbolo.toLowerCase().equals("exit");
    }

    /**
     * Aplica el simbolo exit 
     * @param symbol el simbolo exit (no usado)
     * @param args los argumentos de la expresion (no usado)
     * @param contexto el contexto donde se desarrollará la operacion
     * @return el átomo "Bye" al salir del programa
     */
    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        return this.factory.createAtom("Bye");
    }
}
