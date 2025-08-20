
package mx.edu.itses.aja.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class EliminacionGaussiana {
    private int MN; 
    private ArrayList<Double> matrizA;  
    private ArrayList<Double> vectorB; 
    private ArrayList<Double> vectorX; 
    private double[][] matriz;          
    private double[] resultados;    
    private double[] solucion;   
}
