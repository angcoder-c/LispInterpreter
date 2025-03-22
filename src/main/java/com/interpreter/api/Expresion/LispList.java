package com.interpreter.api.Expresion;

import java.util.ArrayList;
import java.util.List;

import com.interpreter.api.Environment;
import com.interpreter.api.Apply.LispOperator;
import com.interpreter.api.Apply.OperacionCond;
import com.interpreter.api.Apply.OperacionesAritmeticas;
import com.interpreter.api.Apply.OperacionesSistema;
import com.interpreter.api.Apply.OperacionQuote;
import com.interpreter.api.Apply.OperacionSetq;
import com.interpreter.api.Apply.OperacionDefun;
import com.interpreter.api.Apply.OperacionLlamadaFuncion;
import com.interpreter.api.Apply.OperacionesBooleanas;
import com.interpreter.api.Apply.OperacionIf;
import com.interpreter.api.Apply.OperacionesLogicas;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcela Castillo
 * Creación: 27/02/2025
 * última modificación: 27/02/2025
 * File Name: LispList.java
 * Descripción: LispExpression que representa una lista de lisp
 */

public class LispList implements LispExpression {
    private List<LispExpression> elementos;
    private List<LispOperator> operadores = new ArrayList<>();
    private LispExpressionFactory factory;
    private Environment contexto;

    public LispList(List<LispExpression> elementos, Environment contexto) {
        this.elementos = elementos;
        this.contexto = contexto;
        this.factory = new LispExpressionFactory(this.contexto);
        initializeOperators();
    }

    /**
     * metodo que agrega los operadores del lenguaje al arreglo
     * de operadores, que será iterado para evaluar el código
     */
    private void initializeOperators() {
        this.operadores.clear();
        this.operadores.add(new OperacionesAritmeticas(this.contexto));
        this.operadores.add(new OperacionQuote(this.contexto));
        this.operadores.add(new OperacionDefun(this.contexto));
        this.operadores.add(new OperacionesSistema(this.contexto));
        this.operadores.add(new OperacionesBooleanas(this.contexto));
        this.operadores.add(new OperacionIf(this.contexto));
        this.operadores.add(new OperacionSetq(this.contexto));
        this.operadores.add(new OperacionesLogicas(this.contexto));
        this.operadores.add(new OperacionCond(this.contexto));
        // siempre abajo
        this.operadores.add(new OperacionLlamadaFuncion(this.contexto));
    }

    /**
     * metodo para limpiar el contexto, y crea un nuevo factory para
     * limpiar el contexto asociado a éste
     * @param nuevoContexto
     */
    public void setContexto(Environment nuevoContexto) {
        this.contexto = nuevoContexto;
        this.factory = new LispExpressionFactory(nuevoContexto);
        initializeOperators();
    }

    /**
     * @return elementos de la lista
     */
    public List<LispExpression> getElementos() {
        return elementos;
    }

    // retorna el tipo de dato
    @Override
    public String getType() {
        return "List";
    }

    @Override
    public LispExpression evaluate() {
        if (elementos.isEmpty()) {
            return this;
        }

        // operador
        LispExpression first = elementos.get(0);//.evaluate();

        if (first instanceof LispAtom) {
            String operation = first.toString();

            // recorre el array de operadores hasta que coincida con
            // una operación soportada por el interprete
            // luego se le aplica
            for (int i=0; i<operadores.size(); i++) {
                LispOperator op = operadores.get(i);
                if (op.supports(operation)) {
                    List<LispExpression> args = elementos.subList(1, elementos.size());
                    return op.apply(operation, args, this.contexto);
                }
            }

            return this.factory.createAtom("ERROR: operación no soportada");
        }
        return this;
    }

    @Override
    public String toString() {
        if (elementos.isEmpty()) {
            return "()";
        }

        String result = "(" + elementos.get(0).toString();

        for (int i = 1; i < elementos.size(); i++) {
            result += " " + elementos.get(i).toString();
        }

        return result + ")";
    }
}