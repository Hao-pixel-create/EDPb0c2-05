/*********************************************************************
 *  
 * Nombre de la clase: Leible
 * Autor/es: Haojie Zhou
 * Fecha de creación: 03/10/2025
 * Versión: 1.0
 * Descripción de la clase: 
 *      Interfaz que define el comportamiento que deben implementar 
 *      los objetos capaces de leer sus datos desde un fichero de texto 
 *      secuencial. Cada clase que implemente esta interfaz debe indicar 
 *      cómo extraer sus campos desde un array de cadenas.
 * 
 *********************************************************************/
public interface Leible {

    /*********************************************************************
     * 
     * Nombre del método: leerDatos
     * 
     * Descripción del método:
     *      Recibe un array de cadenas que representan una línea del fichero
     *      y asigna los valores correspondientes a los atributos del objeto.
     * 
     * Argumentos:
     *      @param campos (String[]) - Array que contiene los datos separados
     *                                 por el delimitador del fichero.
     * 
     * Valor devuelto:
     *      void – No devuelve ningún valor.
     * 
     *********************************************************************/
    void leerDatos(String[] campos);
}
