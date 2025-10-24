package p2;

import java.util.Scanner;
import java.util.Stack;

/**
 * Operadores:
 *  + : binario, concatena dos cadenas de caracteres.
 *  - : binario, elimina de la primera todos los caracteres presentes en la segunda.
 *  @ : unario, invierte una cadena usando una pila auxiliar.
 *  * : binario, intersecta dos cadenas, seleccionando los caracteres presentes en ambas.
 *
 * Requisitos técnicos:
 *  - Uso de Stack<String> (sin raw types ni Object).
 *  - Documentación en métodos relevantes.
 *  - Sin datos personales; usar iniciales/código de equipo si se documentan autores.
 *
 * Ejemplos:
 *  Entrada:  abc xyz + @
 *  Salida final: zyxcba
 *
 *  Entrada:  risoto raudo - @ sino *
 *  Salida final: si
 *
 *  Entrada:  hola adios + mundo + /
 *  Muestra el vaciado de la pila con "/".
 */
public class PincipalPila {
    private static final Scanner TECLADO = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Introduce la expresión posfija separadas por espacio:");
        if (!TECLADO.hasNextLine()) {
            System.err.println("No se ha leido nada");
            return;
        }

        String cadena = TECLADO.nextLine().trim();
        if (cadena.isEmpty()) {
            System.err.println("La expresión está vacía.");
            return;
        }

        Stack<String> pila = new Stack<>();
        String[] tokens = cadena.split(" +");

        for (String tk : tokens) {
            switch (tk) {
                case "+": {
                    if (pila.size() < 2) {
                        Metodos.errorOperandos(tk);
                        return;
                    }
                    String b = pila.pop();
                    String a = pila.pop();
                    String r = a + b;
                    pila.push(r);
                    System.out.println("operando '+': \"" + a + "\" + \"" + b + "\" = \"" + r + "\"");
                    break;
                }
                case "-": {
                    if (pila.size() < 2) {
                        Metodos.errorOperandos(tk);
                        return;
                    }
                    String b = pila.pop();
                    String a = pila.pop();
                    String r = Metodos.quitarCaracteres(a, b);
                    pila.push(r);
                    System.out.println("operando '-': \"" + a + "\" - {" + b + "} = \"" + r + "\"");
                    break;
                }
                case "@": {
                    if (pila.isEmpty()) {
                        Metodos.errorOperandos(tk);
                        return;
                    }
                    String a = pila.pop();
                    String r = Metodos.invertirConPila(a);
                    pila.push(r);
                    System.out.println("operando '@': reverse(\"" + a + "\") = \"" + r + "\"");
                    break;
                }
                case "*": {
                    if (pila.size() < 2) {
                        Metodos.errorOperandos(tk);
                        return;
                    }
                    String b = pila.pop();
                    String a = pila.pop();
                    String r = Metodos.interseccion(a, b);
                    pila.push(r);
                    System.out.println("operando '*': interseccion(\"" + a + "\", \"" + b + "\") = \"" + r + "\"");
                    break;
                }
                case "/": {
                    System.out.println("operando '/': volcado de pila:");
                    if (pila.isEmpty()) {
                        System.out.println("  [pila vacía]");
                    } else {
                        while (!pila.isEmpty()) {
                            System.out.println("  " + pila.pop());
                        }
                    }
                    break;
                }
                default: {
                    pila.push(tk);
                    System.out.println("Push: \"" + tk + "\"  (el tamaño de la pila es = " + pila.size() + ")");
                }
            }
        }

        if (!pila.isEmpty()) {
            System.out.println("la cima de la pila es : " + pila.peek());
        } else {
            System.out.println(" la pila esta vacía.");
        }
    }
}
