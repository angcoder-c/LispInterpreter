package com.interpreter.api;

import java.util.ArrayList;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 26/02/2025
 * última modificación: 26/02/2025
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
     * @author Marcela Castillo
     * @param String input string con codigo lisp para tokenizar
     * @return una lista de strings con los tokens
     */
    public List<String> read_str(String input) {
        List<String> tokens = new ArrayList<>();

        //Se convierte la expresión a un array de caracteres
        char[] caracteres = input.toCharArray();
        StringBuilder acumulado = new StringBuilder(); 

        for (char x : caracteres) {
            if ((x >= '0' && x <= '9') || (x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z')) {
                // Si es un número o letra lo agregamos al acumulador
                acumulado.append(x);
            } else {
                // Si encontramos un espacio o un paréntesis, guardamos el token acumulado
                if (acumulado.length() > 0) {
                    tokens.add(acumulado+"");
                    acumulado.setLength(0); // Reiniciar acumulado
                }
                
                // Agregamos el paréntesis u operador como token separado
                if (x != ' ') {
                    tokens.add(x + "");  
                }
            }
        }
        
        // Si al final quedó un token pendiente (en el caso que termine en un número o variable)
        if (acumulado.length() > 0) {
            tokens.add(acumulado+"");
        }

        return tokens;
    }

}