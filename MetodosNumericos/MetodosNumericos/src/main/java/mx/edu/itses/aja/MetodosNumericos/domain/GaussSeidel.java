
package mx.edu.itses.aja.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;
@Data
public class GaussSeidel {
    private int MN; // dimensión de la matriz
    private ArrayList<Double> matrizA;  // matriz de coeficientes (A)
    private ArrayList<Double> vectorB;  // vector de términos independientes (B)
    private ArrayList<Double> x0;       // vector de valores iniciales
    private int iteraciones;            // número de iteraciones máximas
    private double tolerancia;          // error permitido
    private ArrayList<Double> vectorX;  
}
