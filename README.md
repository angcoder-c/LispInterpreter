# Java Common Lisp Interpreter 

```
     _                         ____  _      _
    | |  __ _ __   __  __ _   / ___|| |    (_) ___  _ __
 _  | | / _` |\ \ / / / _` | | |    | |    | |/ __|| '_ \
| |_| || (_| | \ V / | (_| | | |___ | |___ | |\__ \| |_) |
 \___/  \__,_|  \_/   \__,_|  \____||_____||_||___/| .__/
                                                   |_|
```
[https://github.com/angcoder-c/LispInterpreter/edit/main/README.md](https://github.com/angcoder-c/LispInterpreter/edit/main/README.md)

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
mvn test
```

## Caracteristicas

**Multilinea:** No
**Operaciones:**

El intérprete soporta las siguientes operadores de Lisp:

- **Operaciones aritméticas**: `+`, `-`, `*`, `/`
- **Operaciones de comparación**: `>`, `<`, `>=`, `<=`, `=`, `/=`
- **Operaciones lógicas**: `and`, `or`, `not`
- **Definición de variables**: `setq`
- **Definición de funciones**: `defun`
- **Estructuras de control**: `if`, `cond`
- **Evaluación de expresiones**: `quote`

# Diseño

**Diagrama UML de clases**

![image](https://github.com/user-attachments/assets/44c272c7-ff1a-4134-a7c5-a806e5dffb17)


**Diagrama de estados**

![image](https://github.com/user-attachments/assets/87d93064-e935-4da0-afe0-ea06b96bde6a)

**Diagramas de secuencia**

**Diagrama de secuencia de la operación DEFUN**

![WhatsApp Image 2025-03-22 at 08 30 29_1dd4df8b](https://github.com/user-attachments/assets/0f7139fe-7950-44e3-b245-9993a39a7624)

**Diagrama de secuencia de la operación LlamarFuncion**

![image](https://github.com/user-attachments/assets/25eed338-7150-42ff-bebf-6b2e49c2f59d)

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


## Paso 3: Environment

La clase `Environment.java` representa el contexto del intérprete, en donde se almacenan tanto las funciones definidias por el usuario como las variables definidas por el usuario, o parametros definidos para una función.

Se implementaron dos HashMaps:

- `funciones`: almacena las funciones definidas por el usuario.
- `variables`: almacena las variables junto a sus valores correspondientes.

Se definieron metodo CRUD para controlar el contexto:

- `definirFuncion`: nueva función.
- `definirVariable`: asigna una variable con un valor.
- `obtenerFuncion`: recupera una función definida en el contexto.
- `obtenerVariable`: recupera una variable definida.
- `existeFuncion`: verifica una función está definida
- `existeVariable`: verifica si una variable existe
- `eliminarVariable`: permite eliminar una variable del contexto.

**Funcionamiento**


## Paso 4: LispExpression

`LispExpression.java` ayuda a representar elementos de la sintaxis de lisp por medio de un interfaz general, que define tres métodos principales:

- `getType`: devuelve el tipo de la expresión (Number, Symbol, List, Function)
- `evaluate`: evalúa la expresión y devuelve un resultado
- `toString`: representa la expresión en un string

Cuenta con dos implementaciones generales:

- `LispAtom`: representa cualquier cosa que no sea una lista.
- `LispList`: representa listas de expresiones, admite listas dentro de listas y átomos.

Y tres implementaciones especificas:

- `LispNumber`: representa valores numéricos
- `LispFunction`: representa funciones definidas por el usuario

Además, se implementó el patrón  de diseño Factory mediante `LispExpressionFactory.java` para crear expresiones con un mismo contexto.

**Funcionamiento**



## Paso 5: Evaluator

El proceso de evaluación está distribuido entre las expresiones y los operadores, respetando el principio de responsabilidad única. Las principales clases involucradas son:

`LispOperator`: interfaz que define el contrato de un operador con dos métodos:

- `support`: evalua sin un operador es soportado por el interprete. Retorna un valor booleano.
- `apply`: aplica la lógica correspondiente al operador.

Las implementaciones de operadores que soporta el interprete son:

- `OperacionesAritmeticas`: para `+`, `-`, `*`, `/`
- `OperacionesBooleanas`: comparaciones (`>`, `<`, `>=`, `<=`, `=`)
- `OperacionesLogicas`: para operaciones lógicas (`and`, `or`, `not`)
- `OperacionQuote`: para la operación quote
- `OperacionDefun`: para definir funciones
- `OperacionSetq`: para asignar variables
- `OperacionIf`: para condicionales
- `OperacionCond`: para multicondicionales
- `OperacionLlamadaFuncion`: para invocar funciones definidas por el usuario.

El evaluador está integrado en `LispList.evaluate()`, que:

- Identifica la operación
- Busca un operador que soporte la operación
- Aplica el operador con los argumentos
- Devuelve el resultado de la evaluación

**Funcionamiento**


## Paso 6: REPL

El REPL en la clase `App.java`, que proporciona una interfaz para introducir comandos de lisp:

- `Read`: lee la entrada del usuario mediante un Scanner
- `Eval`: utiliza el parser para analizar la entrada y evaluarla
- `Print`: muestra el resultado de la evaluación
- `Loop`: Repite el proceso hasta que el usuario ingrese `(exit)`

Características:

**Funcionamiento**

