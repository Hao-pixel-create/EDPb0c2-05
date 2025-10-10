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

    String nombre;       
    String designador;   
    String epoch;        
    double movimientoMedio; 
    double inclinacion;     

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
            if (campos.length < 6)
                return;

            nombre = campos[0];
            designador = campos[1];
            epoch = campos[2];
            movimientoMedio = Double.parseDouble(campos[3]);
            inclinacion = Double.parseDouble(campos[5]);

        } catch (NumberFormatException e) {
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
