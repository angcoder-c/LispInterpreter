package com.interpreter.api;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.interpreter.api.Apply.OperacionDefun;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispList;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 22/03/2025
 * última modificación: 22/03/2025
 * File Name: OperacionDefunTest.java
 * Descripción: Pruebas con JUnit para la definición de funciones por el usuario
 */

class OperacionDefunTest {

    private OperacionDefun operacionDefun;
    private Environment contexto;
    private LispExpressionFactory factory;

    /**
     * Se crean las distintas expresiones y objetos necesarios para los tests
     */
    @BeforeEach
    void setUp() {
        contexto = new Environment(); // Asume que Environment tiene un constructor por defecto
        factory = new LispExpressionFactory(contexto);
        operacionDefun = new OperacionDefun(contexto);
    }

    /**
     * Verifica que los simbolos ingresados sean los reservados para DEFUn
     */
    @Test
    void testSupports() {
        assertTrue(operacionDefun.supports("defun"));
        assertTrue(operacionDefun.supports("DEFUN"));
    }

    /**
     * Verifica que la funcion se defina correctamente
     */
    @Test
    void testApplyCorrecto() {
        List<LispExpression> args = Arrays.asList(
            new LispAtom("suma", contexto), // Nombre de la función
            new LispList(Arrays.asList(new LispAtom("a", contexto), new LispAtom("b", contexto)), contexto), // Parámetros
            new LispList(Arrays.asList(new LispAtom("+", contexto), new LispAtom("a", contexto), new LispAtom("b", contexto)), contexto) // Cuerpo
        );

        LispExpression resultado = operacionDefun.apply("defun", args, contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("suma", resultado.toString());

        assertTrue(contexto.existeFuncion("suma"));
    }

    /**
     * Verificación de cuando no hay argumentos suficientes
     */
    @Test
    void testApplyArgumentosInsuficientes() {
        List<LispExpression> args = Arrays.asList(
            new LispAtom("suma", contexto)
        );

        LispExpression resultado = operacionDefun.apply("defun", args, contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("ERROR: defun necesita nombre, parametros y cuerpo", resultado.toString());
    }
}