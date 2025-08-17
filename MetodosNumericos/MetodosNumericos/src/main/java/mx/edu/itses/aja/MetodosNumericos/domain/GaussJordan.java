
package mx.edu.itses.aja.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class GaussJordan {
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> vectorX;
    private int MN;
}
