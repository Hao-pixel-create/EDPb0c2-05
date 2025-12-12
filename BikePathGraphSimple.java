import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.util.*;

public class BikePathGraphSimple {

    public static void main(String[] args) throws IOException {
        // Leer el archivo CSV
        List<String> lines = Files.readAllLines(Paths.get("bikeways.csv"));

        // Variables para los cálculos
        int totalLines = lines.size() - 1; // Descontamos la cabecera
        int selectedSegments = 0;
        double totalDistance = 0;
        Set<String> intersections = new HashSet<>();
        Map<String, Integer> intersectionDegree = new HashMap<>();

        // Procesar cada línea del archivo CSV (excepto la cabecera)
        for (int i = 1; i < lines.size(); i++) {
            String[] data = lines.get(i).split(";");

            // Validar los campos numéricos antes de procesarlos
            if (data.length < 19) continue;  // Si la línea no tiene suficientes datos, saltarla

            double speedLimit = parseDouble(data[10].trim());
            double segmentLength = parseDouble(data[17].trim());
            int yearOfConstruction = parseInt(data[18].trim());

            // Filtrar según los criterios
            if (speedLimit >= 30 && segmentLength > 10 && yearOfConstruction >= 1990) {
                selectedSegments++;

                // Obtener las coordenadas de inicio y fin
                String[] startCoords = data[22].split(" ")[0].replace("[", "").replace("]", "").split(",");
                String[] endCoords = data[22].split(" ")[1].replace("[", "").replace("]", "").split(",");

                // Validar las coordenadas antes de redondearlas
                if (startCoords.length != 2 || endCoords.length != 2) {
                    System.out.println("Error con las coordenadas en la línea: " + lines.get(i));
                    continue;
                }

                // Redondear las coordenadas
                String startPoint = roundCoordinate(startCoords[0].trim()) + "," + roundCoordinate(startCoords[1].trim());
                String endPoint = roundCoordinate(endCoords[0].trim()) + "," + roundCoordinate(endCoords[1].trim());

                // Agregar las intersecciones
                intersections.add(startPoint);
                intersections.add(endPoint);

                // Actualizar el grado de las intersecciones
                intersectionDegree.put(startPoint, intersectionDegree.getOrDefault(startPoint, 0) + 1);
                intersectionDegree.put(endPoint, intersectionDegree.getOrDefault(endPoint, 0) + 1);

                // Acumular la distancia total
                totalDistance += segmentLength;
            }
        }

        // Cálculos finales
        int numIntersections = intersections.size();
        int numSegments = selectedSegments;
        double averageDistance = totalDistance / numSegments;

        // Número de intersecciones con grado mayor que 2
        int highDegreeIntersections = 0;
        for (int degree : intersectionDegree.values()) {
            if (degree > 2) {
                highDegreeIntersections++;
            }
        }

        // Resultados con formato de salida ajustado a 2 decimales
        double selectedPercentage = (double) selectedSegments / totalLines * 100;

        System.out.printf("Número de intersecciones: %d\n", numIntersections);
        System.out.printf("Número total de segmentos: %d\n", numSegments);
        System.out.printf("Porcentaje de segmentos seleccionados: %.2f%%\n", selectedPercentage);
        System.out.printf("Distancia media de los segmentos: %.2f m\n", averageDistance);
        System.out.printf("Número de intersecciones con grado superior a 2: %d\n", highDegreeIntersections);
    }

    // Método para redondear las coordenadas a 5 decimales usando BigDecimal
    public static String roundCoordinate(String value) {
        // Eliminar cualquier caracter extraño y espacios en blanco
        value = value.trim();

        // Asegurarse de que la cadena sea válida para BigDecimal
        try {
            BigDecimal bd = new BigDecimal(value);
            // Redondear a 5 decimales
            bd = bd.setScale(5, RoundingMode.HALF_UP);
            // Convertir el BigDecimal redondeado a float y devolverlo como String
            return bd.floatValue() + "";
        } catch (NumberFormatException e) {
            System.out.println("Error con la coordenada: " + value);
            return "0.00000"; // Valor por defecto en caso de error
        }
    }

    // Método para analizar valores de tipo Double con validación
    public static double parseDouble(String value) {
        try {
            if (value.isEmpty()) return 0;
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("Error con el valor Double: " + value);
            return 0; // Valor por defecto en caso de error
        }
    }

    // Método para analizar valores de tipo Integer con validación
    public static int parseInt(String value) {
        try {
            if (value.isEmpty()) return 0;
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Error con el valor Integer: " + value);
            return 0; // Valor por defecto en caso de error
        }
    }
}
