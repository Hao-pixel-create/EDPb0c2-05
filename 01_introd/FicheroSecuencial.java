import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*********************************************************************
 *  
 * Nombre de la clase: FicheroSecuencial
 * Autor/es: Haojie Zhou
 * Fecha de creación: 03/10/2025
 * Versión: 1.0
 * Descripción de la clase:
 *      Clase genérica que permite gestionar la lectura secuencial 
 *      de ficheros de texto. No depende de la estructura interna del 
 *      fichero, delegando la interpretación de cada línea al objeto 
 *      genérico T (que debe implementar la interfaz Leible).
 * 
 *********************************************************************/
public class FicheroSecuencial<T extends Leible> {

    BufferedReader lector;     
    String separador;         
    boolean finFichero;        
    boolean cabeceraLeida;     

    /*********************************************************************
     * 
     * Nombre del método: FicheroSecuencial (Constructor)
     * 
     * Descripción del método:
     *      Abre el fichero de texto indicado y prepara el lector para 
     *      leerlo de forma secuencial.
     * 
     * Argumentos:
     *      @param nombreFichero (String) - Nombre o ruta del fichero a abrir.
     *      @param separador (String) - Carácter usado como delimitador entre campos.
     * 
     * Valor devuelto:
     *      Ninguno (constructor).
     * 
     * Ficheros requeridos:
     *      El fichero indicado debe existir en el directorio de trabajo.
     * 
     * Excepciones comprobadas:
     *      IOException - Si ocurre un error al abrir el fichero.
     * 
     *********************************************************************/
    public FicheroSecuencial(String nombreFichero, String separador) throws IOException {
        lector = new BufferedReader(new FileReader(nombreFichero));
        this.separador = separador;
        this.finFichero = false;
        this.cabeceraLeida = false;
    }

    /*********************************************************************
     * 
     * Nombre del método: cerrar
     * 
     * Descripción del método:
     *      Cierra el flujo de lectura del fichero y libera los recursos.
     * 
     * Argumentos:
     *      Ninguno.
     * 
     * Valor devuelto:
     *      void - No devuelve ningún valor.
     * 
     * Excepciones comprobadas:
     *      IOException - Si ocurre un error al cerrar el fichero.
     * 
     *********************************************************************/
    public void cerrar() throws IOException {
        if (lector != null)
            lector.close();
    }

    /*********************************************************************
     * 
     * Nombre del método: finFichero
     * 
     * Descripción del método:
     *      Indica si se ha alcanzado el final del fichero.
     * 
     * Argumentos:
     *      Ninguno.
     * 
     * Valor devuelto:
     *      boolean - true si no quedan líneas por leer; false en caso contrario.
     * 
     *********************************************************************/
    public boolean finFichero() {
        return finFichero;
    }

    /*********************************************************************
     * 
     * Nombre del método: leerRegistro
     * 
     * Descripción del método:
     *      Lee una línea del fichero, separa sus campos con el separador 
     *      indicado y llama al método leerDatos() del objeto genérico T 
     *      para que interprete sus datos. La cabecera se salta de forma 
     *      automática sin modificar el fichero.
     * 
     * Argumentos:
     *      @param objeto (T) - Objeto genérico que procesará los datos leídos.
     * 
     * Valor devuelto:
     *      boolean - true si la lectura se ha realizado correctamente,
     *                 false si se ha llegado al final del fichero.
     * 
     * Ficheros requeridos:
     *      El fichero debe haber sido abierto correctamente.
     * 
     * Excepciones comprobadas:
     *      IOException - Si ocurre un error al leer del fichero.
     * 
     *********************************************************************/
    public boolean leerRegistro(T objeto) throws IOException {
        String linea;

        if (!cabeceraLeida) {
            lector.readLine();
            cabeceraLeida = true;
        }

        linea = lector.readLine();

        if (linea == null) {
            finFichero = true;
            return false;
        }

        String[] campos = linea.split(separador, -1);

        objeto.leerDatos(campos);

        return true;
    }

    /*********************************************************************
     * 
     * Nombre del método: saltarLinea
     * 
     * Descripción del método:
     *      Permite avanzar una línea en el fichero sin procesarla.
     * 
     * Argumentos:
     *      Ninguno.
     * 
     * Valor devuelto:
     *      void - No devuelve ningún valor.
     * 
     * Excepciones comprobadas:
     *      IOException - Si ocurre un error al saltar la línea.
     * 
     *********************************************************************/
    public void saltarLinea() throws IOException {
        if (lector.readLine() == null)
            finFichero = true;
    }
}
