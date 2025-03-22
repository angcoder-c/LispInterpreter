package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispList;
import com.interpreter.api.Expresion.LispFunction;

import java.util.ArrayList;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionDefun.java
 * Descripción: implementa la funcion defun de lisp
 * para definir una nueva función
 */

public class OperacionDefun implements LispOperator {
    private LispExpressionFactory factory;

    /**
     * Constructor correspondiente
     * @param contexto el contexto donde se definirá la nueva función
     */
    public OperacionDefun (Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }
    /**
     * Verifica que el simbolo ingresado como argumento sea el reservado para la operación defun 
     * @param symbol el operador para definir una funcion (defun)
     * @return verdadero si se soporta el operador, falso si no se soporta
     */
    @Override
    public boolean supports(String symbol) {
        return symbol.toLowerCase().equals("defun");
    }

    /**
     * Define una nueva función en el contexto indicado
     * @param symbol el simbolo que representa la operación (defun)
     * @param args una lista de expresiones con el nombre de la funcion (primer elemento) y la lista de parámetros (segundo elemento)
     * @param contexto el entorno donde se define la nueva función
     * @return el átomo con el nombre de la función en caso se haya definido correctamente
     */
    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.size() < 3) {
            return this.factory.createAtom(
                "ERROR: defun necesita nombre, parametros y cuerpo"
            );
        }

        String nombreFuncion = args.get(0).toString();
        LispExpression parametrosExpr = args.get(1);
        LispExpression cuerpo = args.get(2);

        if (!(parametrosExpr instanceof LispList)) {
            return this.factory.createAtom(
                "ERROR: los parametros deben ser una lista"
            );
        }

        LispList lista = (LispList) parametrosExpr;
        List<String> parametros = new ArrayList<>();
        for (Object elem : lista.getElementos()) {
            parametros.add(elem.toString());
        }

        contexto.definirFuncion(nombreFuncion, new LispFunction(parametros, cuerpo, contexto));
        return this.factory.createAtom(nombreFuncion);
    }
}
