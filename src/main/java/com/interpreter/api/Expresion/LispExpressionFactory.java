package com.interpreter.api.Expresion;

import java.util.List;

public class LispExpressionFactory {
    public static LispExpression createAtom(String valor) {
        return new LispAtom(valor);
    }

    public static LispExpression createList(List<LispExpression> elementos) {
        return new LispList(elementos);
    }
}
