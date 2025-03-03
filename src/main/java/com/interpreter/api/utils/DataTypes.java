package com.interpreter.api.utils;

public class DataTypes {
    public static String lispType(String valor) {
        if (DataTypes.isNumber(valor)) {
            return "Number";
        }

        if (
            valor.equalsIgnoreCase("true") ||
            valor.equalsIgnoreCase("false")
        ) {
            return "Boolean";
        }

        // if (DataTypes.isString(valor)) {
        //    return "String";
        //}
        return "Symbol";
    }

    private static boolean isNumber (String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        }  catch (Exception e) {
            return false;
        }
    }
}
