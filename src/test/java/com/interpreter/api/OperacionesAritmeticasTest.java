package com.interpreter.api;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.interpreter.api.Apply.OperacionesAritmeticas;
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
 * File Name: OperacionesAritmeticasTest.java
 * Descripción: Pruebas con JUnit para los métodos de las operaciones aritméticas
 */


 
 class OperacionesAritmeticasTest {
 
     private OperacionesAritmeticas operaciones;
     private Environment contexto;
     private LispExpressionFactory factory;
 
     /**
     * Se crean las distintas expresiones y objetos necesarios para los tests
     */

    @BeforeEach
     void setUp() {
         contexto = new Environment();
         factory = new LispExpressionFactory(contexto);
         operaciones = new OperacionesAritmeticas(contexto);
     }
 
     /**
     * Se comprueba que las verificaciones de operadores sean correctas
     */
    @Test
     void testSupports() {
         assertTrue(operaciones.supports("+"));
         assertTrue(operaciones.supports("-"));
         assertTrue(operaciones.supports("*"));
         assertTrue(operaciones.supports("/"));
         assertFalse(operaciones.supports("%"));
     }
 
     /**
     * Se verifica la realización correcta de sumas
     */
     @Test
     void testApplySuma() {
         LispExpression resultado = operaciones.apply("+", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
         assertTrue(resultado instanceof LispNumber);
         assertEquals(5.0, ((LispNumber) resultado).getValue());
     }
 
     /**
     * Se verifica la realización correcta de resta
     */
     @Test
     void testApplyResta() {
         LispExpression resultado = operaciones.apply("-", Arrays.asList(new LispNumber(5), new LispNumber(3)), contexto);
         assertTrue(resultado instanceof LispNumber);
         assertEquals(2.0, ((LispNumber) resultado).getValue());
     }
 
     /**
     * Se verifica la realización correcta de multiplicaciones
     */
     @Test
     void testApplyMultiplicacion() {
         LispExpression resultado = operaciones.apply("*", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
         assertTrue(resultado instanceof LispNumber);
         assertEquals(6.0, ((LispNumber) resultado).getValue());
     }
 
     /**
     * Se verifica la realización correcta de divisiones
     */
     @Test
     void testApplyDivision() {
         LispExpression resultado = operaciones.apply("/", Arrays.asList(new LispNumber(6), new LispNumber(3)), contexto);
         assertTrue(resultado instanceof LispNumber);
         assertEquals(2.0, ((LispNumber) resultado).getValue());
     }
 
     /**
     * Se verifica la división entre 0
     */
     @Test
     void testApplyDivisionPorCero() {
         LispExpression resultado = operaciones.apply("/", Arrays.asList(new LispNumber(6), new LispNumber(0)), contexto);
         assertTrue(resultado instanceof LispAtom);
         assertEquals("ERROR: división por cero", resultado.toString());
     }
 
     /**
     * Se verifican los argumentos insuficientes
     */
     @Test
     void testApplyArgumentosInsuficientes() {
         LispExpression resultado = operaciones.apply("+", Arrays.asList(new LispNumber(2)), contexto);
         assertTrue(resultado instanceof LispAtom);
         assertEquals("ERROR: argumentos insuficientes", resultado.toString());
     }
 
     /**
     * Se verifica la a aplicación de sumas a datos no numéricos
     */
     @Test
     void testApplyOperandosNoNumericos() {
         LispExpression resultado = operaciones.apply("+", Arrays.asList(new LispAtom("a", contexto), new LispAtom("b", contexto)), contexto);
         assertTrue(resultado instanceof LispAtom);
         assertEquals("ERROR: los operandos deben ser numeros", resultado.toString());
     }
 
     /**
     * Aplicación del contexto a las sumas, utilizando variables
     */
     @Test
     void testApplyOperandosComoVariables() {
         contexto.definirVariable("x", new LispNumber(5));
         contexto.definirVariable("y", new LispNumber(3));
 
         LispExpression resultado = operaciones.apply("+", Arrays.asList(new LispAtom("x", contexto), new LispAtom("y", contexto)), contexto);
         assertTrue(resultado instanceof LispNumber);
         assertEquals(8.0, ((LispNumber) resultado).getValue());
     }
 
     /**
     * Intento de operaciones no definidas como módulo
     */
     @Test
     void testApplyOperacionDesconocida() {
         LispExpression resultado = operaciones.apply("%", Arrays.asList(new LispNumber(2), new LispNumber(3)), contexto);
         assertTrue(resultado instanceof LispAtom);
         assertEquals("ERROR: operacion desconocida", resultado.toString());
     }
 }