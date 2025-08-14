package mx.edu.itses.aja.MetodosNumericos.domain;

import lombok.Data;

@Data
public class NewtonRaphson {

    private int iteracion;
    private double XI;
    private String FX;
    private String FDX;
    private double Ea;
    public double XR;
    
    private int iteracionesMaximas;
    
    
}
