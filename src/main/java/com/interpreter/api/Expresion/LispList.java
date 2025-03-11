package com.interpreter.api.Expresion;

import java.util.List;

public class LispList implements LispExpression {
    private List<LispExpression> elementos;

    public LispList(List<LispExpression> elementos) {
        this.elementos = elementos;
    }

    @Override
    public String getType() {
        return "List";
    }

    @Override
    public LispExpression evaluate() {
        return this;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < elementos.size(); i++) {
            result += elementos.get(i).toString() + " ";  
        }
        return result + "]";
    }
}

