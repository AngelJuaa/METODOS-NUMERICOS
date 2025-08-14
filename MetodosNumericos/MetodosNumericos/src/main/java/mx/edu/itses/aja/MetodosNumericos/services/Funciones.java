package mx.edu.itses.aja.MetodosNumericos.services;

<<<<<<< HEAD
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
=======
import static java.lang.Math.abs;
import org.mariuszgromada.math.mxparser.*;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
>>>>>>> 62c339775070e1c1db0ea6ef85e07c23bb284a57

public class Funciones {

    // Método para evaluar una expresión matemática con una variable x
    public static double Ecuacion(String funcion, double x) {
        try {
            // Normaliza la función para reemplazar expresiones comunes
            funcion = normalizarFuncion(funcion, x);

            // Usa JavaScript Engine para evaluar la expresión
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

<<<<<<< HEAD
            // Evalúa la función
            Object resultado = engine.eval(funcion);
            return Double.parseDouble(resultado.toString());

        } catch (ScriptException | NumberFormatException e) {
            System.err.println("Error al evaluar la función: " + e.getMessage());
            return Double.NaN;
        }
    }

    // Método para calcular el error relativo porcentual
    public static double ErrorRelativo(double nuevo, double anterior) {
        return Math.abs((nuevo - anterior) / nuevo) * 100;
    }

    // Método para convertir funciones como "sen(x)", "ln(x)", etc. a formato compatible con JavaScript
    private static String normalizarFuncion(String funcion, double x) {
        // Reemplazos comunes
        funcion = funcion.replaceAll("sen", "Math.sin");
        funcion = funcion.replaceAll("cos", "Math.cos");
        funcion = funcion.replaceAll("tan", "Math.tan");
        funcion = funcion.replaceAll("log", "Math.log10"); // log base 10
        funcion = funcion.replaceAll("ln", "Math.log");    // log natural
        funcion = funcion.replaceAll("sqrt", "Math.sqrt");
        funcion = funcion.replaceAll("abs", "Math.abs");

        // Reemplaza potencias estilo x^2 por Math.pow(x, 2)
        funcion = funcion.replaceAll("([a-zA-Z0-9\\.]+)\\^([0-9\\.]+)", "Math.pow($1,$2)");

        // Reemplaza la variable x por su valor numérico
        funcion = funcion.replaceAll("x", "(" + x + ")");

        return funcion;
    }
=======
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

>>>>>>> 62c339775070e1c1db0ea6ef85e07c23bb284a57
}
