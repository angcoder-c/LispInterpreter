# Java Common Lisp Interpreter 

## Diagrama de clases UML

![image](https://github.com/user-attachments/assets/ff4ad4ad-5dd0-491f-9bf3-3617b641d3a3)

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
mvn exec:java '-Dexec.mainClass="com.api.App"'
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




