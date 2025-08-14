
package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
<<<<<<< HEAD
=======
import mx.edu.itses.aja.MetodosNumericos.domain.NewtonRaphson;
>>>>>>> 62c339775070e1c1db0ea6ef85e07c23bb284a57
import mx.edu.itses.aja.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.aja.MetodosNumericos.domain.Secante;
import mx.edu.itses.aja.MetodosNumericos.domain.SecanteModificado;


public interface UnidadllService {
    
     public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
     public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa);
     public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo);
<<<<<<< HEAD
=======
     public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonraphson);
     public ArrayList<Secante> AlgoritmoSecante(Secante secante);
     public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado secantemodificado);
>>>>>>> 62c339775070e1c1db0ea6ef85e07c23bb284a57
}
