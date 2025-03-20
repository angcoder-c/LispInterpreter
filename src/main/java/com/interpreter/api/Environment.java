package com.interpreter.api;

import java.util.HashMap;
import java.util.Map;

import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispFunction;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Angel Chavez
 * Creación: 18/03/2025
 * última modificación: 19/02/2025
 * File Name: Environment.java
 * Descripción: Contexto del interprete
 */

public class Environment {
    // objetos para almacenar las funciones y variables
    private Map<String, LispFunction> funciones;
    private Map<String, LispExpression> variables;

    public Environment() {
        this.funciones = new HashMap<>();
        this.variables = new HashMap<>();
    }

    /**
     * @param nombre nombre de la nueva función a definir
     * @param funcion objeto LispFunction que representa una nueva función a almacenar
     */
    public void definirFuncion(String nombre, LispFunction funcion) {
        funciones.put(nombre, funcion);
    }
    
    /**
     * @param valor objeto LispAtom que representa una nueva función a almacenar
     */
    public void definirVariable(String nombre, LispExpression valor) {
        variables.put(nombre, valor);
    }

    /**
     * @param nombre nombre de la función a obtener
     * @return LispFunction correspondiente
     */
    public LispFunction obtenerFuncion(String nombre) {
        return funciones.get(nombre);
    }
    
    /**
     * @param nombre nombre de la variable a obtener
     * @return LispAtom correspondiente a la clave
     */
    public LispExpression obtenerVariable(String nombre) {
        return variables.get(nombre);
    }

    /**
     * si la función está en el array de funciones, se evalua como true
     * @param nombre nombre de la función a buscar
     * @return true si esta dentro del array, y flase si no lo está
     */
    public boolean existeFuncion(String nombre) {
        return funciones.containsKey(nombre);
    }
    
    /**
     * si la función está en el array de variables, se evalua como true
     * @param nombre nombre de la variable a buscar
     * @return true si esta dentro del array, y flase si no lo está
     */
    public boolean existeVariable(String nombre) {
        return variables.containsKey(nombre);
    }

    /**
     * elimina una variable del array de variables
     * @param nombre nombre de la variable a buscar
     */
    public void eliminarVariable(String nombre) {
        variables.remove(nombre);
    }

    @Override
    public String toString() {
        if (funciones.isEmpty()) {
            return "---";
        }
        
        String result = "";
        for (Map.Entry<String, LispFunction> entry : funciones.entrySet()) {
            result += "f- " + entry.getKey() + " -> " + entry.getValue().toString() + "\n";
        }

        result += "\nv:\n";
        for (Map.Entry<String, LispExpression> entry : variables.entrySet()) {
            result += "v- " + entry.getKey() + " -> " + entry.getValue().toString() + "\n";
        }
        return result;
    }

}