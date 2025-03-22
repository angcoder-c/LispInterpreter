package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispList;
import com.interpreter.api.Expresion.LispNumber;

import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: OperacionCond.java
 * Descripción: implementa el operador quote 
 */

public class OperacionCond implements LispOperator {
    private LispExpressionFactory factory;
    
    /**
     * Constructor correspondiente
     * @param contexto contexto donde se están llevando a cabo las operaciones
     */
    public OperacionCond(Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }
    
    /**
    * Verifica que el simbolo ingresado como argumento sea el reservado para la operación cond 
    * @param symbol el simbolo ingresado para una condicional (cond)
    * @return verdadero si se soporta el operador, falso si no se soporta
    */
    @Override
    public boolean supports(String symbol) {
        return symbol.toLowerCase().equals("cond");
    }
    
    /**
     * @param symbol El simbolo que representa la operación (no utilizado en este método)
     * @param args La lista de expresiones condicionales, la primera es la condicion y las siguienteslas expresiones a evaluar
     * si es verdadera
     * @param contexto El entorno en el que se evaluan las expresiones
     * @return el resultado de evaluar la expresion en caso la condicoin sea verdadera
     * si no, se devuelve NIL o un mensaje de error en caso de no ser una lista o una lista vacía
     */
    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.isEmpty()) {
            return factory.createAtom("nil"); // nill
        }
        //Se verifica que las clausulas sean listas
        for (LispExpression clausula : args) {
            if (!(clausula instanceof LispList)) {
                return factory.createAtom(
                    "ERROR: todas las clausulas deben ser listas"
                );
            }
            
            LispList listaClausula = (LispList) clausula;
            List<LispExpression> elementosClausula = listaClausula.getElementos();
            
            if (elementosClausula.isEmpty()) {
                return factory.createAtom(
                    "ERROR: cláusula de cond vacía"
                );
            }
            
            // Se evalúa la condicional
            LispExpression condicion = elementosClausula.get(0).evaluate();
            
            if (isTrue(condicion)) {
                if (elementosClausula.size() == 1) {
                    return condicion; 
                }
                
                // evaluar elementos y devolver el ultimo
                LispExpression resultado = factory.createAtom("nil");
                for (int i = 1; i < elementosClausula.size(); i++) {
                    resultado = elementosClausula.get(i).evaluate();
                }
                return resultado;
            }
        }
        
        // Sino, se devuelve nil
        return factory.createAtom("nil");
    }
    
    /**
     * @param expr la expresión a comprobar que sea verdadera
     * @return true si la expresión evaluada es verdadera o false si no lo es
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
            // true: si el numero es diferente de cero
            // false: si el numero es igual a cero
            double valor = ((LispNumber) expr).getValue();
            return valor != 0;
        }
        return true;
    }
}