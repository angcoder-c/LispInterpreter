package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispNumber;

import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionesLogicas.java
 * Descripción: implementa comparadores booleanos basicos
 */

public class OperacionesLogicas implements LispOperator {
    private LispExpressionFactory factory;

    /**
     * Constructor correspondiente
     * @param contexto entorno donde se desarrollarán las operaciones lógicas
     */
    public OperacionesLogicas(Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }


    /**
     * Verifica que el simbolo ingresado sea uno de los reservados para las operaciones lógicas
     * @param simbolo el operador lógico a realizar
     * @return verdadero si se puede llevar a cabo la operación lógica, false si no
     */
    @Override
    public boolean supports(String simbolo) {
        return (
            simbolo.toLowerCase().equals("and") || 
            simbolo.toLowerCase().equals("or") || 
            simbolo.toLowerCase().equals("not")
        );
    }

    /**
     * Aplica la operación lógica especificada
     * @param simbolo operador a aplicar.
     * @param args la lista de expresiones con los dos valores a comparar
     * @param contexto entorno donde se desarrolla la operacion
     * @return el resultado de la operación lógica en forma de átomo (true o false)
     */
    @Override
    public LispExpression apply(String simbolo, List<LispExpression> args, Environment contexto) {
        if (simbolo.equals("not")) {
            if (args.size() != 1) {
                return factory.createAtom("ERROR: requiere 1 argumento");
            }
            return evaluateNot(args.get(0).evaluate());

        } else if (simbolo.equals("and")) {    
            return evaluateAnd(args, contexto);
        
        } else if (simbolo.equals("or")) {
            return evaluateOr(args, contexto);
        }

        return factory.createAtom("ERROR: no soportado");
    }

    /**
     * Operación NOT
     * @param expr La expresión a evaluar.
     * @return "true" si expr es falsa, "false" si expr es verdadera.
     */
    private LispExpression evaluateNot(LispExpression expr) {
        if (isTrue(expr)) {
            return factory.createAtom("false");
        } else {
            return factory.createAtom("true");
        }
    }

    /**
     * Operación AND
     * @param args las expresión a comparar
     * @param contexto el entorno donde se desarrollan las operaciones
     * @return true si todos los argumentos son verdaderos, false por lo contrario
     */
    private LispExpression evaluateAnd(List<LispExpression> args, Environment contexto) {
        if (args.isEmpty()) {
            return factory.createAtom("true"); 
        }

        LispExpression result = factory.createAtom("true");
        
        for (LispExpression arg : args) {
            LispExpression evaluated = arg.evaluate();
            
            if (!isTrue(evaluated)) {
                return factory.createAtom("false");
            }
            result = evaluated;
        }
        
        return result;
    }

    /**
     * Operación OR
     * @param args la lista de expresiones a comparar 
     * @param contexto el contexto donde se realizarán las comparaciones
     * @return true si al menos uno es verdadero
     */
    private LispExpression evaluateOr(List<LispExpression> args, Environment contexto) {
        if (args.isEmpty()) {
            return factory.createAtom("false");
        }

        for (LispExpression arg : args) {
            LispExpression evaluated = arg.evaluate();
            
            if (isTrue(evaluated)) {
                return evaluated;
            }
        }
        
        return factory.createAtom("false");
    }

    /**
     * Determina si una expresión es verdadera.
     * @param expr el valor booleano a comprobar
     * @return true si expr es distinta de "nil", "false" o "0"
     */
    private boolean isTrue(LispExpression expr) {
        if (expr instanceof LispAtom) {
            String valor = expr.toString();
            return !(
                valor.equalsIgnoreCase("nil") || 
                valor.equalsIgnoreCase("false") || 
                valor.equals("0")
            );
        } else if (expr instanceof LispNumber) {
            double valor = ((LispNumber) expr).getValue();
            return valor != 0;
        }
        return true;
    }
}