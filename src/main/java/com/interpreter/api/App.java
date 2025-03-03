package com.interpreter.api;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        
        String option = "";
        Scanner sc = new Scanner(System.in);

        while (!option.equals("(exit)")) {
            System.out.print(">>> ");
            String lisp = sc.nextLine();

            Read read = new Read();
            boolean correct = read.verificarPerentesis(lisp);
            List<String> tokens = read.read_str(lisp);

            System.out.println("Codigo " + (correct ? "valido" : "invalido"));
            System.out.println("Tokens: ");

            for (int i=0; i<tokens.size(); i++) {
                System.out.print("\"" + tokens.get(i) + "\"" + ",\n");
            }

            option = lisp;
        }

        
    }
}
