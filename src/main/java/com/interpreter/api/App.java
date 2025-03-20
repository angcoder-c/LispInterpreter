package com.interpreter.api;

import java.util.List;
import java.util.Scanner;
import com.github.lalyos.jfiglet.FigletFont;

public class App {
    public static void main(String[] args) {
        
        
        String option = "";
        Scanner sc = new Scanner(System.in);
        Environment contexto = new Environment();
        Parser parser = new Parser(contexto);

        String asciiArt = FigletFont.convertOneLine("Java CLisp");
        System.out.println(asciiArt + "v.0.0.1");
        System.out.println("\tJulián Divas | Marcela Castillo | Angel Chavez\n");

        while (!option.equals("(exit)")) {
            System.out.print(">>> ");
            try {
                String lisp = sc.nextLine();
                if (!lisp.trim().isEmpty()) {
                    System.out.println(parser.parse(lisp).evaluate());
                }
                option = lisp;
            } catch (Exception e) {
                System.out.println("\nBye");
                break;
            }
        }

        
    }
}
