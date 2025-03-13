# Java Common Lisp Interpreter 

[https://github.com/angcoder-c/LispInterpreter](https://github.com/angcoder-c/LispInterpreter)

## Diagrama de clases UML

![image](https://github.com/user-attachments/assets/6762c10d-1518-485c-9038-8a66f23edccf)

# Ejecución

**Instalación**

```bash
mvn install
```

---

**Compilación**

```bash
mvn package
```

**Correr**

```bash
mvn exec:java '-Dexec.mainClass="com.interpreter.api.App"'
```

**Test**

```bash
mvn -Dtest=AppTest test
```


# Desarrollo

## Paso 1: Lexer

Para inicial, se implementó un Lexer en la clase `Lexer.java`. Encargada de tokenizar y validar una instrucción de Lisp

Se compone de dos métodos escenciales:

- `validarParentesis`: que valida que cada parentesis que se habra, esté cerrado.
- `read_str`: toma un fragmento de código lisp y lo descompone el tokens.

**Funcionamiento**

![tructures_LispInterpreter2025-03-0314-24-08-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/59faf9c6-c879-46d5-aaa0-7891da59311e)

## Paso 2: Parser

Se implementaron las siguientes expresiones de Lisp, en el paquete `expression` que implementan la interfaz `LisoExpression`:
- `LispAtom`: representa un átomo de lisp.
- `LispList`: representa una lista de lisp.

En la clase `Parser.java` en donde se define los métodos:
- `isAtom`: verifica que un string sea un átomo de lisp
- `parse`: método principal del parser, en donde se valida el código de entrada y se tokeniza.
- `parseExpression`: método que recorre la lista de tokens de forma recursiva.

**Funcionamiento**

![parser](https://github.com/user-attachments/assets/fa0b2f8b-f595-4c2f-8026-cc1371bf7e9f)



