package com.interpreter.api.Expresion;

public interface LispExpression {
    /*
     * @author Angel chavez
     */
    String getType();
    LispExpression evaluate();
    String toString();
}
