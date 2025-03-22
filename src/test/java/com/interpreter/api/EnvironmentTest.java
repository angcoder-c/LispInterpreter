package com.interpreter.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispFunction;
import com.interpreter.api.Expresion.LispExpressionFactory;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcela Castillo
 * Creación: 22/03/2025
 * última modificación: 22/03/2025
 * File Name: EnvironmentTest.java
 * Descripción: Pruebas con JUnit para los métodos del Environment
 */

/**
 * Clase que contiene las pruebas unitarias para la clase {@link Environment}.
 * Estas pruebas aseguran que los métodos de definición, obtención, eliminación y representación
 * de funciones y variables en el contexto de un intérprete Lisp funcionen correctamente.
 */
class EnvironmentTest {

    private Environment contexto;
    private LispFunction function;
    private LispExpression variable;
    private LispExpressionFactory factory;

    /**
     * Inicializa los objetos necesarios para las pruebas.
     * Este método se ejecuta antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        contexto = new Environment();
        factory = new LispExpressionFactory(contexto);
        function = new LispFunction(List.of("param1", "param2"), null, contexto); // Asume una estructura básica para LispFunction
        variable = factory.createAtom("10"); // Usando el factory para crear una expresión atómica simple
    }

    /**
     * Verifica que se pueda definir una nueva función en el entorno y que sea recuperable.
     */
    @Test
    void testDefinirFuncion() {
        contexto.definirFuncion("miFuncion", function);
        assertTrue(contexto.existeFuncion("miFuncion"));
        assertEquals(function, contexto.obtenerFuncion("miFuncion"));
    }

    /**
     * Verifica que se pueda definir una nueva variable en el entorno y que sea recuperable.
     */
    @Test
    void testDefinirVariable() {
        contexto.definirVariable("miVariable", variable);
        assertTrue(contexto.existeVariable("miVariable"));
        assertEquals(variable, contexto.obtenerVariable("miVariable"));
    }

    /**
     * Verifica que se retorne {@code null} al intentar obtener una función que no existe en el entorno.
     */
    @Test
    void testObtenerFuncionInexistente() {
        assertNull(contexto.obtenerFuncion("funcionInexistente"));
    }

    /**
     * Verifica que se retorne {@code null} al intentar obtener una variable que no existe en el entorno.
     */
    @Test
    void testObtenerVariableInexistente() {
        assertNull(contexto.obtenerVariable("variableInexistente"));
    }

    /**
     * Verifica que se pueda eliminar una variable del entorno y que ya no sea accesible después de eliminarla.
     */
    @Test
    void testEliminarVariable() {
        contexto.definirVariable("miVariable", variable);
        contexto.eliminarVariable("miVariable");
        assertFalse(contexto.existeVariable("miVariable"));
    }

    /**
     * Verifica la representación en cadena del entorno cuando existen funciones y variables definidas.
     * La salida esperada incluye las funciones y variables definidas con sus valores respectivos.
     */
    @Test
    void testToString() {
        contexto.definirFuncion("func1", function);
        contexto.definirVariable("var1", variable);

        String expectedOutput = "f- func1 -> func\n\nv:\nv- var1 -> var(10)\n";
        assertEquals(expectedOutput, contexto.toString());
    }

    /**
     * Verifica que una función se pueda definir y eliminar correctamente del entorno.
     */
    @Test
    void testDefinirYEliminarFuncion() {
        contexto.definirFuncion("miFuncion", function);
        assertTrue(contexto.existeFuncion("miFuncion"));
        contexto.eliminarVariable("miFuncion");
        assertFalse(contexto.existeFuncion("miFuncion"));
    }

    /**
     * Verifica que la representación en cadena del entorno sea "---" cuando no existen funciones ni variables definidas.
     */
    @Test
    void testToStringCuandoEstaVacio() {
        String expectedOutput = "---";
        assertEquals(expectedOutput, contexto.toString());
    }
}

