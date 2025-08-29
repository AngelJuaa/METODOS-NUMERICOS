package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.aja.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.aja.MetodosNumericos.domain.Lagrange;
import org.springframework.stereotype.Service;

@Service
public class UnidadIVServicelmpl implements UnidadIVService {

    @Override
    public DDNewton AlgoritmoDDNewton(DDNewton modelNewton) {
        ArrayList<Double> x = modelNewton.getPuntosX();
        ArrayList<Double> y = modelNewton.getPuntosY();
        double punto = modelNewton.getPuntoInterpolacion();

        int n = x.size();
        ArrayList<ArrayList<Double>> tabla = new ArrayList<>();

        // Inicializar la tabla de diferencias divididas
        ArrayList<Double> primeraFila = new ArrayList<>(y);
        tabla.add(primeraFila);

        // Calcular diferencias divididas
        for (int i = 1; i < n; i++) {
            ArrayList<Double> fila = new ArrayList<>();
            for (int j = 0; j < n - i; j++) {
                double diferencia = (tabla.get(i - 1).get(j + 1) - tabla.get(i - 1).get(j)) / (x.get(j + i) - x.get(j));
                fila.add(diferencia);
            }
            tabla.add(fila);
        }

        // Construir el polinomio
        StringBuilder polinomio = new StringBuilder();
        polinomio.append(String.format("%.4f", tabla.get(0).get(0)));

        for (int i = 1; i < n; i++) {
            polinomio.append(" + ").append(String.format("%.4f", tabla.get(i).get(0)));
            for (int j = 0; j < i; j++) {
                polinomio.append("*(x - ").append(String.format("%.4f", x.get(j))).append(")");
            }
        }

        // Calcular valor interpolado
        double resultado = tabla.get(0).get(0);
        for (int i = 1; i < n; i++) {
            double termino = tabla.get(i).get(0);
            for (int j = 0; j < i; j++) {
                termino *= (punto - x.get(j));
            }
            resultado += termino;
        }

        modelNewton.setTablaDiferencias(tabla);
        modelNewton.setPolinomio(polinomio.toString());
        modelNewton.setValorInterpolado(resultado);

        return modelNewton;
    }

    @Override
    public Lagrange AlgoritmoLagrange(Lagrange modelLagrange) {
        ArrayList<Double> x = modelLagrange.getPuntosX();
        ArrayList<Double> y = modelLagrange.getPuntosY();
        double punto = modelLagrange.getPuntoInterpolacion();

        int n = x.size();
        double resultado = 0;
        StringBuilder polinomio = new StringBuilder();

        for (int i = 0; i < n; i++) {
            double termino = y.get(i);
            StringBuilder terminoStr = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    termino *= (punto - x.get(j)) / (x.get(i) - x.get(j));
                    if (j > 0) {
                        terminoStr.append("*");
                    }
                    terminoStr.append("(x - ").append(String.format("%.4f", x.get(j))).append(")/")
                            .append(String.format("%.4f", (x.get(i) - x.get(j))));
                }
            }

            resultado += termino;

            if (i > 0) {
                polinomio.append(" + ");
            }
            polinomio.append(String.format("%.4f", y.get(i))).append("*[").append(terminoStr).append("]");
        }

        modelLagrange.setPolinomio(polinomio.toString());
        modelLagrange.setValorInterpolado(resultado);

        return modelLagrange;
    }

}
