package com.interpreter.api;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.interpreter.api.Apply.OperacionesLogicas;
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
 * File Name: OperacionesLogicas.java
 * Descripción: Pruebas con JUnit para los métodos de las operaciones lógicas
 */

 class OperacionesLogicasTest {

    private OperacionesLogicas operaciones;
    private Environment contexto;
    private LispExpressionFactory factory;

    /**
     * Se crean las distintas expresiones y objetos necesarios para los tests
     */
    @BeforeEach
    void setUp() {
        contexto = new Environment();
        factory = new LispExpressionFactory(contexto);
        operaciones = new OperacionesLogicas(contexto);
    }

    /**
     * Se comprueba que las verificaciones de operadores sean correctas
     */
    @Test
    void testSupports() {
        assertTrue(operaciones.supports("and"));
        assertTrue(operaciones.supports("or"));
        assertTrue(operaciones.supports("not"));
        assertFalse(operaciones.supports("xor"));
    }

    /**
     * Se comprueba la aplicación correcta del operador not a true
     */
    @Test
    void testApplyNotTrue() {
        LispExpression resultado = operaciones.apply("not", Arrays.asList(new LispAtom("true", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("false", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador not a false
     */
    @Test
    void testApplyNotFalse() {
        LispExpression resultado = operaciones.apply("not", Arrays.asList(new LispAtom("false", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador not a nil
     */
    @Test
    void testApplyNotNil() {
        LispExpression resultado = operaciones.apply("not", Arrays.asList(new LispAtom("nil", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador not a 0, que también representa false
     */
    @Test
    void testApplyNotCero() {
        LispExpression resultado = operaciones.apply("not", Arrays.asList(new LispNumber(0)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador not a 1, que también representa true
     */
    @Test
    void testApplyNotUno() {
        LispExpression resultado = operaciones.apply("not", Arrays.asList(new LispNumber(1)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("false", resultado.toString());
    }

    /**
     * Se comprueba la verificación de la cantidad de argumentos
     */
    @Test
    void testApplyArgumentosInsuficientes() {
        LispExpression resultado = operaciones.apply("not", Arrays.asList(), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("ERROR: requiere 1 argumento", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador and a dos True
     */
    @Test
    void testApplyAndTrues() {
        LispExpression resultado = operaciones.apply("and", Arrays.asList(new LispAtom("true", contexto), new LispAtom("true", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador and a true y false
     */
    @Test
    void testApplyAndTrueFalse() {
        LispExpression resultado = operaciones.apply("and", Arrays.asList(new LispAtom("true", contexto), new LispAtom("false", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("false", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador and a dos false
     */
    @Test
    void testApplyFalses() {
        LispExpression resultado = operaciones.apply("and", Arrays.asList(new LispAtom("false", contexto), new LispAtom("false", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("false", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador or a dos True
     */
    @Test
    void testApplyOrTrues() {
        LispExpression resultado = operaciones.apply("or", Arrays.asList(new LispAtom("true", contexto), new LispAtom("true", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Se comprueba la aplicación correcta del operador or a true false
     */
    @Test
    void testApplyOrTrueFalse() {
        LispExpression resultado = operaciones.apply("or", Arrays.asList(new LispAtom("true", contexto), new LispAtom("false", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("true", resultado.toString());
    }

    /**
     * Se comprueba la verificación de operadores no definidas
     */
    @Test
    void testApplyUnsupportedOperation() {
        LispExpression resultado = operaciones.apply("xor", Arrays.asList(new LispAtom("true", contexto), new LispAtom("false", contexto)), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("ERROR: no soportado", resultado.toString());
    }
}