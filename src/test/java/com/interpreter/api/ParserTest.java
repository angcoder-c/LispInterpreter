package com.interpreter.api;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.interpreter.api.Expresion.LispExpression;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 11/02/2025
 * última modificación: 11/02/2025
 * File Name: Parser.java
 * Descripción: Pruebas con JUnit para los métodos de Parser.java
 */

public class ParserTest {

    //Pruebas del método isAtom
    //Prueba con Atom como número
    @Test
    public void numeroAtom(){
        List<String> tokens = Arrays.asList("42");
        assertTrue(Parser.isAtom(tokens));
    }

    //Prueba con Atom como String
    public void stringAtom(){
        List<String> tokens = Arrays.asList("Frijolitos");
        assertTrue(Parser.isAtom(tokens));
    }

    //Prueba con paréntesis de apertura
    public void aperturaAtom(){
        List<String> tokens = Arrays.asList("(");
        assertTrue(Parser.isAtom(tokens));
    }

    //Prueba con paréntesis de cerradura
    public void cerraduraAtom(){
        List<String> tokens = Arrays.asList(")");
        assertTrue(Parser.isAtom(tokens));
    }

    //Prueba con una lista
    public void listaAtom(){
        List<String> tokens = Arrays.asList("Frijolitos", "54");
        assertFalse(Parser.isAtom(tokens));
    }

    //Pruebas del método Parse
    //Prueba con una expresión lisp valida
    @Test
    public void LispValidaParse() {
        Parser parser = new Parser();
        String lispCode = "(+ 6 9)";
        LispExpression result = parser.parse(lispCode);
        assertEquals("[+ 6 9 ]", result.toString());
    }

    //Prueba con una expresión lisp sin cerrar
    @Test
    public void LispSinCerradura() {
        Parser parser = new Parser();
        String lispCode = "(+ 1 4";
        LispExpression result = parser.parse(lispCode);
        assertEquals("ERROR: lista no cerrada", result.toString());
    }

    //Prueba con un Atom
    @Test
    public void LispAtom() {
        Parser parser = new Parser();
        String lispCode = "65";
        LispExpression result = parser.parse(lispCode);
        assertEquals("65", result.toString());
    }
}  