package mx.edu.itses.aja.MetodosNumericos.services;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Funciones {

    // Método para evaluar una expresión matemática con una variable x
    public static double Ecuacion(String funcion, double x) {
        try {
            // Normaliza la función para reemplazar expresiones comunes
            funcion = normalizarFuncion(funcion, x);

            // Usa JavaScript Engine para evaluar la expresión
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

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
}
