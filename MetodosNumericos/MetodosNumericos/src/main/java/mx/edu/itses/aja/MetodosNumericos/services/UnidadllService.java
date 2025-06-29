
package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.aja.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.aja.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.aja.MetodosNumericos.domain.Secante;


public interface UnidadllService {
    
     public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
     public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa);
     public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo);
     public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonraphson);
     public ArrayList<Secante> AlgoritmoSecante(Secante secante);
}
