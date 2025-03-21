package com.interpreter.api;

import java.util.ArrayList;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 26/02/2025
 * última modificación: 19/03/2025
 * File Name: Read.java
 * Descripción: Lexer para el interprete de Lisp
 */

public class Lexer {

    /**
     * @author Julián Divas
     * @param lisp la expresión en formato Lisp para revisar
     * @return true si los paréntesis están correctamente escritos en lisp, false si hay un error sintáctico
     */
    public boolean verificarPerentesis(String lisp){
        //Contadores de aperturas y cerraduras de paréntesis
        int aperturas = 0;
        int cerraduras = 0;
        //Se convierte la expresión en lisp a un array de caracteres
        char[] caracteres = lisp.toCharArray();
        //Para cada caracter en el array de caracteres
        for (char caracter: caracteres){
            //Se cuentan los paréntesis de apertura
            if (caracter == '('){
                aperturas += 1;
            }
            //Se cuentan los paréntesis de cerradura
            else if(caracter == ')'){
                cerraduras += 1;
            }
        }
        //La cantidad de paréntesis debe ser igual
        if (aperturas == cerraduras){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * tokeniza una cadena de código Lisp.
     *
     * @author Marcela Castillo
     * @param input String con código Lisp para tokenizar.
     * @return Una lista de strings con los tokens.
     */
    public List<String> read_str(String input) {
        List<String> tokens = new ArrayList<>();
        char[] caracteres = input.toCharArray();
        StringBuilder acumulado = new StringBuilder();
        boolean stringFlag = false;
        boolean numeroFlag = false;
        boolean puntoFlag = false;
    
        List<String> operadores = List.of("<=", ">=", "==", "!=", "+", "-", "*", "/", "%");
    
        for (int i = 0; i < caracteres.length; i++) {
            char x = caracteres[i];
    
            // verifica operadores dobles
            if (i + 1 < caracteres.length) {
                String posibleOperador = "" + x + caracteres[i + 1];
                if (operadores.contains(posibleOperador)) {
                    if (acumulado.length() > 0) {
                        tokens.add(acumulado.toString());
                        acumulado.setLength(0);
                    }
                    tokens.add(posibleOperador);
                    i++;
                    continue;
                }
            }
    
            // strings
            if (stringFlag) {
                acumulado.append(x);
                if (x == '"') {
                    tokens.add(acumulado.toString());
                    acumulado.setLength(0);
                    stringFlag = false;
                }
                continue;
            }
    
            if (x == '"') {
                if (acumulado.length() > 0) {
                    tokens.add(acumulado.toString());
                    acumulado.setLength(0);
                }
                acumulado.append(x);
                stringFlag = true;
                continue;
            }
    
            // paréntesis como tokens separados
            if (x == '(' || x == ')') {
                if (acumulado.length() > 0) {
                    tokens.add(acumulado.toString());
                    acumulado.setLength(0);
                }
                tokens.add(String.valueOf(x));
                continue;
            }
    
            // espacios = fin de token
            if (Character.isWhitespace(x)) {
                if (acumulado.length() > 0) {
                    tokens.add(acumulado.toString());
                    acumulado.setLength(0);
                }
                numeroFlag = false;
                puntoFlag = false;
                continue;
            }
    
            // números o negativos válidos
            if (Character.isDigit(x) || (x == '.' && numeroFlag && !puntoFlag)) {
                if (x == '.') puntoFlag = true;
                acumulado.append(x);
                numeroFlag = true;
                continue;
            }
    
            if (x == '-' && i + 1 < caracteres.length && Character.isDigit(caracteres[i + 1]) && acumulado.length() == 0) {
                // negativo solo si no hay símbolo acumulado
                acumulado.append(x);
                numeroFlag = true;
                continue;
            }
    
            // parte de un símbolo: letra, número o guion (en contexto simbólico)
            if (Character.isLetter(x) || Character.isDigit(x) || x == '-') {
                acumulado.append(x);
                continue;
            }
    
            // operadores simples
            if (operadores.contains(String.valueOf(x))) {
                if (acumulado.length() > 0) {
                    tokens.add(acumulado.toString());
                    acumulado.setLength(0);
                }
                tokens.add(String.valueOf(x));
                continue;
            }
    
            // cualquier otro carácter
            if (acumulado.length() > 0) {
                tokens.add(acumulado.toString());
                acumulado.setLength(0);
            }
            tokens.add(String.valueOf(x));
        }
    
        if (acumulado.length() > 0) {
            tokens.add(acumulado.toString());
        }
    
        return tokens;
    }
       

}