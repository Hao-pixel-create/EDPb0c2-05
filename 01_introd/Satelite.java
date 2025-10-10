/*********************************************************************
 *  
 * Nombre de la clase: Satelite
 * Autor/es: Haojie Zhou
 * Fecha de creación: 03/10/2025
 * Versión: 1.0
 * Descripción de la clase:
 *      Representa un satélite meteorológico y contiene algunos de sus
 *      parámetros orbitales, como el movimiento medio y la inclinación.
 *      Sabe cómo leer sus datos desde un fichero CSV con el formato de
 *      Celestrak.
 * 
 *********************************************************************/
public class Satelite implements Leible {

    String nombre;        // Nombre del satélite
    String designador;    // Designador internacional del satélite (ej. "2020-025A")
    String epoch;         // Fecha y hora de referencia del registro orbital
    double movimientoMedio; // Número de órbitas que realiza al día
    double inclinacion;     // Inclinación de la órbita en grados

    /*********************************************************************
     * 
     * Nombre del método: leerDatos
     * 
     * Descripción del método:
     *      Asigna a los atributos del satélite los valores obtenidos de los
     *      campos del fichero CSV.
     * 
     * Argumentos:
     *      @param campos (String[]) - Array que contiene los datos de una línea.
     * 
     * Valor devuelto:
     *      void - No devuelve ningún valor.
     * 
     *********************************************************************/
    @Override
    public void leerDatos(String[] campos) {
        try {
            // Orden de columnas del CSV de Celestrak:
            // 0: OBJECT_NAME, 1: OBJECT_ID, 2: EPOCH, 3: MEAN_MOTION, 5: INCLINATION
            if (campos.length < 6)
                return;

            nombre = campos[0];
            designador = campos[1];
            epoch = campos[2];
            movimientoMedio = Double.parseDouble(campos[3]);
            inclinacion = Double.parseDouble(campos[5]);

        } catch (NumberFormatException e) {
            // Puede fallar si la línea es la cabecera o hay campos vacíos
        }
    }

    public double getMovimientoMedio() {
        return movimientoMedio;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("Satélite: %-25s | Órbitas/día: %.2f | Inclinación: %.2f°",
                nombre, movimientoMedio, inclinacion);
    }
}
