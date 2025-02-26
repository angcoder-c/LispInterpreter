package com.interpreter.api;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 26/02/2025
 * última modificación: 26/02/2025
 * File Name: Parser.java
 * Descripción: Parser para el interprete de Lisp
 */

public class Parser {

    /**
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
}