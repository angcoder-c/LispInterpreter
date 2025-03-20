package com.interpreter.api.Expresion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interpreter.api.Environment;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcela Castillo
 * Creación: 19/03/2025
 * última modificación: 19/03/2025
 * File Name: LispFunction.java
 * Descripción: LispFunction representa una función en lisp
 * definida por el usuario que tiene un nombre, parametros 
 * y un cuerpo
 */

public class LispFunction implements LispExpression {
    private List<String> parametros;
    private LispExpression cuerpo;
    private LispExpressionFactory factory;
    private Environment contexto;

    public LispFunction(List<String> parametros, LispExpression cuerpo, Environment contexto) {
        this.parametros = parametros;
        this.cuerpo = cuerpo;
        this.contexto = contexto;
        this.factory = new LispExpressionFactory(contexto);
    }

    /**
     * metodo para invocar una funcipón definida por el usuario
     * @param argumentos
     * @param contexto
     * @return LispAtom para evaluar al imprimir
     */
    public LispExpression call(List<LispExpression> argumentos, Environment contexto) {
        if (argumentos.size() != parametros.size()) {
            return this.factory.createAtom("ERROR: Cantidad incorrecta de argumentos");
        }

        // almacenar valores anteriores de variables
        Map<String, LispExpression> previousValues = new HashMap<>();
        
        try {
            // relacionar parametros a argumentos
            for (int i = 0; i < parametros.size(); i++) {
                String paramName = parametros.get(i);
                
                // almacenar los valores previos si existen
                if (contexto.existeVariable(paramName)) {
                    previousValues.put(paramName, contexto.obtenerVariable(paramName));
                }
                
                // definir un nuevo valor en el contexto
                contexto.definirVariable(paramName, argumentos.get(i));
            }
            
            // evaluar el cuerpo de la función según el contexto
            return cuerpo.evaluate();

        } finally {
            
            // guardar los elementos previos de nuevo en el contexto
            for (Map.Entry<String, LispExpression> entry : previousValues.entrySet()) {
                contexto.definirVariable(entry.getKey(), entry.getValue());
            }
            
            // eliminar las variables temporales que solo se usan
            // en el scope de la función
            for (String param : parametros) {
                if (!previousValues.containsKey(param)) {
                    contexto.eliminarVariable(param);
                }
            }
        }
    }

    @Override
    public String getType() {
        return "Function";
    }

    @Override
    public LispExpression evaluate() {
        return this;
    }

    @Override
    public String toString() {
        return "<Function>";
    }
}