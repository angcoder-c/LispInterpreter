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
 * @author: Ángel Chavez 
 * Creación: 21/03/2025
 * última modificación: 21/03/2025
 * File Name: OperacionSetQ.java
 * Descripción: se asigna un valor a una
 */

public class OperacionSetq implements LispOperator {
    private LispExpressionFactory factory;
    private Environment contexto;

    /**
     * Constructor correspondiente
     * @param contexto entorno donde se asignará la variable
     */
    public OperacionSetq(Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
        this.contexto = contexto;
    }

    /**
     * Verifica que se utilice el simbolo setq para asignar la variabel
     * @param symbol el simbolo a verificar que sea el reservado de setq
     * @return true si se utilizó setq, false de lo contrario
     */
    @Override
    public boolean supports(String symbol) {
        return symbol.equalsIgnoreCase("setq");
    }

    /**
     * Se asigna el valor corresondiente a la variable indicada
     * @param symbol el operador setq (no utilizado)
     * @param args la lista de expresiones con la variable a utilizar y su valor a agregar
     * @param contexto entorno donde se almacenará la variable y su valor
     * @return el átomo del nombre de la variable asignada
     */
    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.size() < 2) {
            return factory.createAtom("ERROR: setq necesita un nombre y un valor");
        }

        LispExpression nombre = args.get(0);
        LispExpression valor = args.get(1);

        this.contexto.definirVariable(nombre.toString(), valor);

        return this.factory.createAtom(nombre.toString());
    }
}