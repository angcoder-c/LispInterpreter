package com.interpreter.api;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.interpreter.api.Expresion.LispExpression;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas y Marcela Castillo
 * Creación: 11/02/2025
 * última modificación: 12/02/2025
 * File Name: Parser.java
 * Descripción: Pruebas con JUnit para los métodos de Parser.java
 */

public class ParserTest {

    //Pruebas del método isAtom
    //Prueba con Atom como número
    @Test
    public void numeroAtom(){
        assertTrue(Parser.isAtom("42"));
    }

    //Prueba con Atom como String
    public void stringAtom(){
        assertTrue(Parser.isAtom("Frijolitos"));
    }

    //Prueba con paréntesis de apertura
    public void aperturaAtom(){
        assertTrue(Parser.isAtom("("));
    }

    //Prueba con paréntesis de cerradura
    public void cerraduraAtom(){
        assertTrue(Parser.isAtom(")"));
    }

    //Prueba con una lista
    public void listaAtom(){
        assertFalse(Parser.isAtom("Frijolitos 54"));
    }

    //Pruebas del método Parse
    //Prueba con una expresión lisp valida
    @Test
    public void LispValidaParse() {
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);
        String lispCode = "(+ 6 9)";
        LispExpression result = parser.parse(lispCode);
        assertEquals("(+ 6 9)", result.toString());
    }

    //Prueba con una expresión lisp sin cerrar
    @Test
    public void LispSinCerradura() {
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);
        String lispCode = "(+ 1 4";
        LispExpression result = parser.parse(lispCode);
        assertEquals("ERROR: error de sintaxis", result.toString());
    }

    //Prueba con un Atom
    @Test
    public void LispAtom() {
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);
        String lispCode = "65";
        LispExpression result = parser.parse(lispCode);
        assertEquals("65", result.toString());
    }

    //Prueba con lista vacia
    @Test
    public void testListaVacia() {
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);
        String lispCode = "()";
        LispExpression result = parser.parse(lispCode);
        assertEquals("()", result.toString());
    }

    //Prueba para expresiones anidadas
    @Test
    public void testExpresionAnidada() {
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);
        String lispCode = "(+ 1 (* 2 3))";
        LispExpression result = parser.parse(lispCode);
        assertEquals("(+ 1 (* 2 3))", result.toString());
    }

    //Prueba para espacios extra
    @Test
    public void testEspaciosExtra() {
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);
        String lispCode = "(  +   3   7 )";
        LispExpression result = parser.parse(lispCode);
        assertEquals("(+ 3 7)", result.toString());
    }
    
}  