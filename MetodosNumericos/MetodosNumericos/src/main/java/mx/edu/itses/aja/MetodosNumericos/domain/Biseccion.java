package mx.edu.itses.aja.MetodosNumericos.domain;

import lombok.Data;

@Data
public class Biseccion {

    private String FX; //funcion a evaluar
    private double XL;
    private double XU;
    private double XR;
    private double FXL;
    private double FXU;
    private double FXR;
    private double Ea;
    private int IteracionesMaximas;   
}
