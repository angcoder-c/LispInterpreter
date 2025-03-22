package com.interpreter.api;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.interpreter.api.Apply.OperacionLlamadaFuncion;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;
import com.interpreter.api.Expresion.LispFunction;
import com.interpreter.api.Expresion.LispList;
import com.interpreter.api.Expresion.LispNumber;
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
 * File Name: OperacionLlamadaFuncionTest.java
 * Descripción: Pruebas con JUnit para la llamada de funciones
 */

class OperacionLlamadaFuncionTest {

    private OperacionLlamadaFuncion operacionLlamadaFuncion;
    private Environment contexto;
    private LispExpressionFactory factory;

    /**
     * Se crean las distintas expresiones y objetos necesarios para los tests
     */
    @BeforeEach
    void setUp() {
        contexto = new Environment();
        factory = new LispExpressionFactory(contexto);
        operacionLlamadaFuncion = new OperacionLlamadaFuncion(contexto);
    }

    /**
     * Test para cuando se ingresa una variable al método 
     */
    @Test
    void testApplyVariable() {
        contexto.definirVariable("x", new LispNumber(10));
        LispExpression resultado = operacionLlamadaFuncion.apply("x", new ArrayList<>(), contexto);
        assertTrue(resultado instanceof LispNumber);
        assertEquals(10.0, ((LispNumber) resultado).getValue());
    }

    /**
     * Test para cuando se ingresa una funcion definida al método
     */
    @Test
    void testApplyFuncionDefinida() {
        List<String> parametros = Arrays.asList("a", "b");
        LispExpression cuerpo = new LispList(Arrays.asList(new LispAtom("+", contexto), new LispAtom("a", contexto), new LispAtom("b", contexto)), contexto);
        contexto.definirFuncion("suma", new LispFunction(parametros, cuerpo, contexto));

        List<LispExpression> args = Arrays.asList(new LispNumber(2), new LispNumber(3));
        LispExpression resultado = operacionLlamadaFuncion.apply("suma", args, contexto);
        assertTrue(resultado instanceof LispNumber);
        assertEquals(5.0, ((LispNumber) resultado).getValue());
    }

    /**
     * Test de error al ingresar una funcion no definida
     */
    @Test
    void testApplyFuncionNoDefinida() {
        // Llamar a apply con un símbolo que no es una función definida
        LispExpression resultado = operacionLlamadaFuncion.apply("nodefinida", new ArrayList<>(), contexto);
        assertTrue(resultado instanceof LispAtom);
        assertEquals("ERROR: función no definida: nodefinida", resultado.toString());
    }

}