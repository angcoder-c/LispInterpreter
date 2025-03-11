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

            Parser parser = new Parser();
            System.out.println(parser.parse(lisp));

            option = lisp;
        }

        
    }
}
