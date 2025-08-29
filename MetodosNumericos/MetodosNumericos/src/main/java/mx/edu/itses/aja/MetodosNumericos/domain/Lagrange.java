
package mx.edu.itses.aja.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Lagrange {
    private ArrayList<Double> puntosX;
    private ArrayList<Double> puntosY;
    private String polinomio;
    private double valorInterpolado;
    private double puntoInterpolacion;
}
