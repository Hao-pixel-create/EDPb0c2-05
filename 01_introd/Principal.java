import java.io.IOException;

/*********************************************************************
 *  
 * Nombre de la clase: Principal
 * Autor/es: Haojie Zhou
 * Fecha de creación: 03/10/2025
 * Versión: 1.0
 * Descripción de la clase:
 *      Programa principal que utiliza la clase FicheroSecuencial para leer
 *      un fichero CSV con información de satélites meteorológicos y muestra
 *      por pantalla aquellos que realizan más de 10 órbitas al día.
 * 
 *********************************************************************/
public class Principal {

    /*********************************************************************
     * 
     * Nombre del método: main
     * 
     * Descripción del método:
     *      Método principal del programa. Abre el fichero, crea objetos
     *      Satelite y muestra aquellos cuyo movimiento medio sea mayor
     *      a 10 órbitas por día.
     * 
     * Argumentos:
     *      @param args (String[]) - Argumentos pasados por línea de comandos.
     * 
     * Valor devuelto:
     *      void - No devuelve ningún valor.
     * 
     * Ficheros requeridos:
     *      Debe existir un fichero llamado "weather.csv" en el directorio
     *      del proyecto con el formato de Celestrak.
     * 
     * Excepciones comprobadas:
     *      IOException - Si ocurre un error de lectura en el fichero.
     * 
     *********************************************************************/
    public static void main(String[] args) {
        String ruta = "gp.txt"; // Nombre del fichero a leer

        try {
            // Se crea un lector secuencial para objetos de tipo Satelite
            FicheroSecuencial<Satelite> fichero = new FicheroSecuencial<>(ruta, ",");

            System.out.println("=== SATÉLITES CON MÁS DE 10 ÓRBITAS DIARIAS ===\n");

            // Bucle de lectura secuencial
            while (!fichero.finFichero()) {
                Satelite s = new Satelite();

                if (fichero.leerRegistro(s)) {
                    // Mostramos solo los que superen las 10 órbitas diarias
                    if (s.getMovimientoMedio() > 10) {
                        System.out.println(s);
                    }
                }
            }

            fichero.cerrar();
            System.out.println("\nLectura finalizada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
