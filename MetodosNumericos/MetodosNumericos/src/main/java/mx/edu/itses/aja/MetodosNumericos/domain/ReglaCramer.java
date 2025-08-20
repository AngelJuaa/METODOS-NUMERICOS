
package mx.edu.itses.aja.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data

public class ReglaCramer {
    
    private int MN; //j i
    private ArrayList<Double> matrizA;
    private ArrayList<Double> matrizB;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> vectorX;
    private ArrayList<Double> Determinantes;
    private int NumeroRenglon;
    
    public int IncrementarRenglon(){
        return NumeroRenglon++;
    }
}
