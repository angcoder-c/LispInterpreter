package com.interpreter.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.interpreter.api.Apply.OperacionesBooleanas;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispNumber;
import java.util.Arrays;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 22/03/2025
 * última modificación: 22/03/2025
 * File Name: OperacionesBooleanas.java
 * Descripción: Pruebas con JUnit para los métodos de las operaciones booleanas
 */

class OperacionesBooleanasTest {

    private OperacionesBooleanas operaciones;
    private Environment contexto;
    private LispExpressionFactory factory;

    /**
     * Se crean las distintas expresiones y objetos necesarios para los tests
     */
    @BeforeEach
    void setUp() {
        contexto = new Environment();
        factory = new LispExpressionFactory(contexto);
        operaciones = new OperacionesBooleanas(contexto);
    }

    /**
     * Se comprueba que las verificaciones de operadores sean correctas
     */
    @Test
    void testSupports() {
        assertTrue(operaciones.supports("="));
        assertTrue(operaciones.supports("<"));
        assertTrue(operaciones.supports(">"));
        assertTrue(operaciones.supports("<="));
        assertTrue(operaciones.supports(">="));
        assertTrue(operaciones.supports("/="));
        assertFalse(operaciones.supports("+"));
    }

    /**
     * Verifica que se trabajen correctamente las igualdades
     */
    @Test
    void testApplyIgual() {
        LispExpression resultado = operaciones.apply("=", Arrays.asList(new LispNumber(2), new LispNumber(2)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Verifica que se  trabaje correctamente la igualdad
     */
    @Test
    void testApplyIgualdadFalsa() {
        LispExpression resultado = operaciones.apply("=", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("false", resultado.toString());
    }

    /**
     * Verifica que se trabaje correctamente el menor que
     */
    @Test
    void testApplyMenorQue() {
        LispExpression resultado = operaciones.apply("<", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Verifica que se  trabaje correctamente el menor que
     */
    @Test
    void testApplyMayorQue() {
        LispExpression resultado = operaciones.apply(">", Arrays.asList(new LispNumber(5), new LispNumber(3)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Verifica que se trabaje correctamente el menor o igual que
     */
    @Test
    void testApplyMenorOIgualQue() {
        LispExpression resultado = operaciones.apply("<=", Arrays.asList(new LispNumber(2), new LispNumber(2)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Verifica que se trabaje correctamente el mayor o igual que
     */
    @Test
    void testApplyMayorOIgualQue() {
        LispExpression resultado = operaciones.apply(">=", Arrays.asList(new LispNumber(3), new LispNumber(2)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Verifica que se trabaje correctamente el diferente de
     */
    @Test
    void testApplyDiferenteDe() {
        LispExpression resultado = operaciones.apply("/=", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Verifica el error de parámetros insuficientes
     */
    @Test
    void testApplyArgumentosInsuficientes() {
        LispExpression resultado = operaciones.apply("=", Arrays.asList(new LispNumber(2)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("ERROR: la operación booleana requiere exactamente 2 argumentos", resultado.toString());
    }

    /**
     * Verifica que se trabaje con numeros y no letras
     */
    @Test
    void testApplyOperandosLetras() {
        LispExpression resultado = operaciones.apply("=", Arrays.asList(new LispAtom("a", contexto), new LispAtom("b", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("ERROR: los operandos deben ser números", resultado.toString());
    }

    /**
     * Verifica operadores no soportados
     */
    @Test
    void testApplyOperacionNoSoportada() {
        LispExpression resultado = operaciones.apply("%", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("false", resultado.toString());
    }
}