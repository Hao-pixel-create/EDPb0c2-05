package p2;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashSet;

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
public class pilas {
    final static Scanner TECLADO = new Scanner (System.in);
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
                    // Concatena: ... a b + -> ... (a+b)
                    if (pila.size() < 2) {
                        errorOperandos(tk);
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
                    // Resta de caracteres: ... a b - -> ... (a sin los chars de b)
                    if (pila.size() < 2) {
                        errorOperandos(tk);
                        return;
                    }
                    String b = pila.pop();
                    String a = pila.pop();
                    String r = quitarCaracteres(a, b);
                    pila.push(r);
                    System.out.println("operando '-': \"" + a + "\" - {" + b + "} = \"" + r + "\"");
                    break;
                }
                case "@": {
                    // Inversión unaria usando pila auxiliar
                    if (pila.isEmpty()) {
                        errorOperandos(tk);
                        return;
                    }
                    String a = pila.pop();
                    String r = invertirConPila(a);
                    pila.push(r);
                    System.out.println("operando '@': reverse(\"" + a + "\") = \"" + r + "\"");
                    break;
                }
                case "*": {
                    // Intersección: ... a b * -> ... intersect(a,b)
                    if (pila.size() < 2) {
                        errorOperandos(tk);
                        return;
                    }
                    String b = pila.pop();
                    String a = pila.pop();
                    String r = interseccion(a, b);
                    pila.push(r);
                    System.out.println("operando '*': interseccion(\"" + a + "\", \"" + b + "\") = \"" + r + "\"");
                    break;
                }
                case "/": {
                    // Volcado de pila: muestra desde la cima y la deja vacía
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
                    // Operando: lo apilamos tal cual
                    pila.push(tk);
                    System.out.println("Push: \"" + tk + "\"  (el tamaño de la pila es = " + pila.size() + ")");
                }
            }
        }

        // Resultado final (si no se vació con '/')
        if (!pila.isEmpty()) {
            System.out.println("la cima de la pila es : " + pila.peek());
        } else {
            System.out.println(" la pila esta vacía.");
        }
    }

    /**
     * Elimina de 'a' todos los caracteres que aparezcan en 'b'.
     * Mantiene el orden original de 'a'.
     * @param a primera cadena
     * @param b caracteres a eliminar (todos sus caracteres cuentan)
     * @return a sin los caracteres de b
     */
private static String quitarCaracteres(String a, String b) {
    StringBuilder sb = new StringBuilder();
    for (char c : a.toCharArray()) {
        if (b.indexOf(c) == -1) {   // si c NO está en b
            sb.append(c);
        }
    }
    return sb.toString();
}


    /**
     * Intersección de caracteres manteniendo el orden en 'a'.
     * Incluye un carácter de 'a' si está presente (al menos una vez) en 'b'.
     * @param a primera cadena (define el orden del resultado)
     * @param b segunda cadena (define pertenencia)
     * @return intersección de caracteres
     */
     
 private static String interseccion(String a, String b) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < b.length(); i++) {
        char c = b.charAt(i);
        if (a.indexOf(c) != -1) {   // ¿c está en a?
            sb.append(c);
        }
    }
    return sb.toString();
}



    /**
     * Invierte una cadena utilizando explícitamente una pila auxiliar.
     * @param s cadena de entrada
     * @return s invertida
     */
    private static String invertirConPila(String s) {
        Stack<Character> aux = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            aux.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!aux.isEmpty()) {
            sb.append(aux.pop());
        }
        return sb.toString();
    }

    /**
     * Mensaje de error para operadores con operandos insuficientes.
     * @param op operador
     */
    private static void errorOperandos(String operando) {
        System.err.println("Error: operandos insuficientes para el operador '" + operando + "'.");
    }
}
