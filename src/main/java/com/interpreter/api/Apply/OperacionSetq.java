package com.interpreter.api.Apply;

import com.interpreter.api.Environment;
import com.interpreter.api.Expresion.LispAtom;
import com.interpreter.api.Expresion.LispExpression;
import com.interpreter.api.Expresion.LispExpressionFactory;

import java.util.List;

public class OperacionSetq implements LispOperator {
    private LispExpressionFactory factory;
    private Environment contexto;

    public OperacionSetq(Environment contexto) {
        this.factory = new LispExpressionFactory(contexto);
        this.contexto = contexto;
    }

    @Override
    public boolean supports(String symbol) {
        return symbol.equalsIgnoreCase("setq");
    }

    @Override
    public LispExpression apply(String symbol, List<LispExpression> args, Environment contexto) {
        if (args.size() < 2) {
            return factory.createAtom("ERROR: setq necesita un nombre y un valor");
        }

        LispExpression nombre = args.get(0);
        LispExpression valor = args.get(1);

        this.contexto.definirVariable(nombre.toString(), valor);

        return this.factory.createAtom(nombre.toString());
    }
}