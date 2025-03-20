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
    
    public OperacionCond(Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
    }
    
    @Override
    public boolean supports(String symbol) {
        return symbol.toLowerCase().equals("cond");
    }
    
    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.isEmpty()) {
            return factory.createAtom("nil"); // nill
        }
        
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
            
            // evaluar
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
        
        // else false
        return factory.createAtom("nil");
    }
    
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