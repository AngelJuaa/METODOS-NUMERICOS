
package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.aja.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.aja.MetodosNumericos.domain.Lagrange;
import org.springframework.stereotype.Service;

@Service
public interface UnidadIVService {
     public DDNewton AlgoritmoDDNewton(DDNewton modelNewton);
    public Lagrange AlgoritmoLagrange(Lagrange modelLagrange);
}
