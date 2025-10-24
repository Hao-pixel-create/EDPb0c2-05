package p2;

import java.util.Stack;

/**
 * Métodos auxiliares para operar con cadenas y pila.
 */
public final class Metodos {
    private Metodos() {}

    /**
     * Elimina de 'a' todos los caracteres que aparezcan en 'b'.
     * Mantiene el orden original de 'a'.
     * @param a primera cadena
     * @param b caracteres a eliminar (todos sus caracteres cuentan)
     * @return a sin los caracteres de b
     */
    public static String quitarCaracteres(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (char c : a.toCharArray()) {
            if (b.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Intersección de caracteres.
     * Incluye un carácter si aparece en ambas cadenas. **Conserva el orden de 'b'**,
     * @param a primera cadena
     * @param b segunda cadena
     * @return caracteres comunes (orden según 'b')
     */
    public static String interseccion(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            if (a.indexOf(c) != -1) {
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
    public static String invertirConPila(String s) {
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
     * @param operando operador textual
     */
    public static void errorOperandos(String operando) {
        System.err.println("Error: operandos insuficientes para el operador '" + operando + "'.");
    }
}
