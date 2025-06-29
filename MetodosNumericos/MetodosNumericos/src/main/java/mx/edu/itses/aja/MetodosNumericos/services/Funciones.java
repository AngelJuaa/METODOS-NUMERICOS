package mx.edu.itses.aja.MetodosNumericos.services;

import static java.lang.Math.abs;
import org.mariuszgromada.math.mxparser.*;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class Funciones {

    public static double Ecuacion(String f1, double x) {

        double f;
        Function funcion = new Function(f1);
        Expression evaluacion = new Expression("f(" + x + ")", funcion);
        f = evaluacion.calculate();

        return f;
    }

    public static double EvaluarG(String gx, double xi) {
        double resultado;
        Function funcion = new Function(gx);  // g(x) como cadena, por ejemplo: "g(x) = cos(x)"
        Expression evaluacion = new Expression("g(" + xi + ")", funcion);
        resultado = evaluacion.calculate();
        return resultado;
    }

    public static double Derivada(String fx, double x) {
        ExprEvaluator util = new ExprEvaluator();
        IExpr derivada = util.eval("D(" + fx + ", x)");
        String derivadaEvaluar = derivada.toString().replace("x", "(" + x + ")");
        IExpr resultado = util.eval(derivadaEvaluar);
        //return Double.parseDouble(resultado.toString());
        String valor = resultado.toString().replace("*10^","E");
        return Double.parseDouble(valor);
    }

    public static double ErrorRelativo(double ValorNuevo, double ValorAnterior) {
        return abs((ValorNuevo - ValorAnterior) / ValorNuevo * 100);
    }

}
