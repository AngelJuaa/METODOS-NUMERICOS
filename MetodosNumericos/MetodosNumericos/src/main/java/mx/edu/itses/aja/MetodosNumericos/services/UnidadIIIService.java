
package mx.edu.itses.aja.MetodosNumericos.services;

import mx.edu.itses.aja.MetodosNumericos.domain.EliminacionGaussiana;
import mx.edu.itses.aja.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaCramer;


public interface UnidadIIIService {
    public ReglaCramer AlgoritmoReglaCramer (ReglaCramer modelCramer);
    public EliminacionGaussiana AlgoritmoEliminacionGaussiana (EliminacionGaussiana modelGauss);
    public GaussJordan AlgoritmoGaussJordan (GaussJordan modelGaussJordan);
}
