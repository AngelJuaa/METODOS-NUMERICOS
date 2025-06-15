
package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;


public interface UnidadllService {
    
     public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
     public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa);
}
