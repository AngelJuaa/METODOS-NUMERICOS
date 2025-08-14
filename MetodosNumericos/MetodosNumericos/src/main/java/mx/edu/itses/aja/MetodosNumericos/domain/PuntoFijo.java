
package mx.edu.itses.aja.MetodosNumericos.domain;

import lombok.Data;
@Data
public class PuntoFijo {
    private double xi;
    private String gx;
    private double ea;
    private int iteracionesMaximas;
    private double xr;
    private double errorAprox;
   private int iteracion;
}
