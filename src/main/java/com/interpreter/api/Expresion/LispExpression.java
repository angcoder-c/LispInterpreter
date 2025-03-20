package com.interpreter.api.Expresion;

public interface LispExpression {
    /*
     * @author Marcela Castillo
     */
    String getType();
    LispExpression evaluate();
    String toString();
}
