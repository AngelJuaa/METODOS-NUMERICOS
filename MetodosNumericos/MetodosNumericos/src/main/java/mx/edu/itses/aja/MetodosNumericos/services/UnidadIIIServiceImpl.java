package mx.edu.itses.aja.MetodosNumericos.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import mx.edu.itses.aja.MetodosNumericos.domain.EliminacionGaussiana;
import mx.edu.itses.aja.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaCramer;

@Service
@Slf4j

public class UnidadIIIServiceImpl implements UnidadIIIService {

    @Override
    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer) {
        // Almacenamos determinantes de la matriz
        ArrayList<Double> determinantes = new ArrayList<>();
        // Vector de incógnitas
        ArrayList<Double> vectorX = new ArrayList<>();

        switch (modelCramer.getMN()) {
            case 2 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> B = modelCramer.getVectorB();

                double[][] matrizA = {
                    {A.get(0), A.get(1)},
                    {A.get(2), A.get(3)}
                };

                // Determinante principal
                determinantes.add(det2(matrizA));

                // Calculamos determinante para x1
                double[][] matrizX1 = {
                    {B.get(0), A.get(1)},
                    {B.get(1), A.get(3)},};
                determinantes.add(det2(matrizX1));
                log.info("Det X1: " + determinantes.get(1));

                // Calculamos determinante para x2
                double[][] matrizX2 = {
                    {A.get(0), B.get(0)},
                    {A.get(2), B.get(1)},};
                determinantes.add(det2(matrizX2));
                log.info("Det X2: " + determinantes.get(2));

                // Resolviendo las variables
                vectorX.add(determinantes.get(1) / determinantes.get(0)); // x1
                vectorX.add(determinantes.get(2) / determinantes.get(0)); // x2
            }
            case 3 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> B = modelCramer.getMatrizB();

                double[][] matrizA = {
                    {A.get(0), A.get(1), A.get(2)},
                    {A.get(3), A.get(4), A.get(5)},
                    {A.get(6), A.get(7), A.get(8)}
                };

                // Determinante principal
                determinantes.add(det3(matrizA));

                // Calculamos determinante para x1
                double[][] matrizX1 = {
                    {B.get(0), A.get(1), A.get(2)},
                    {B.get(1), A.get(4), A.get(5)},
                    {B.get(2), A.get(7), A.get(8)}
                };
                determinantes.add(det3(matrizX1));
                log.info("Det X1: " + determinantes.get(1));

                // Calculamos determinante para x2
                double[][] matrizX2 = {
                    {A.get(0), B.get(0), A.get(2)},
                    {A.get(3), B.get(1), A.get(5)},
                    {A.get(6), B.get(2), A.get(8)}
                };
                determinantes.add(det3(matrizX2));
                log.info("Det X2: " + determinantes.get(2));

                // Calculamos determinante para x3
                double[][] matrizX3 = {
                    {A.get(0), A.get(1), B.get(0)},
                    {A.get(3), A.get(4), B.get(1)},
                    {A.get(6), A.get(7), B.get(2)}
                };
                determinantes.add(det3(matrizX3));
                log.info("Det X3: " + determinantes.get(3));

                // Resolviendo las variables
                vectorX.add(determinantes.get(1) / determinantes.get(0)); // x1
                vectorX.add(determinantes.get(2) / determinantes.get(0)); // x2
                vectorX.add(determinantes.get(3) / determinantes.get(0)); // x3

            }

        }

        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
    }

    private double det2(double[][] A) {
        return A[0][0] * A[1][1] - A[0][1] * A[1][0];
    }

    private double det3(double[][] A) {
        return A[0][0] * (A[1][1] * A[2][2] - A[1][2] * A[2][1])
                - A[0][1] * (A[1][0] * A[2][2] - A[1][2] * A[2][0])
                + A[0][2] * (A[1][0] * A[2][1] - A[1][1] * A[2][0]);
    }

    @Override
    public EliminacionGaussiana AlgoritmoEliminacionGaussiana(EliminacionGaussiana modelGauss) {
        ArrayList<String> pasos = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList<>();

        // Convertir ArrayList a matriz bidimensional
        ArrayList<Double> A = modelGauss.getMatrizA();
        ArrayList<Double> b = modelGauss.getVectorB();
        int n = modelGauss.getMN();

        double[][] matriz = new double[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index);
                index++;
            }
        }

        double[] resultados = new double[n];
        for (int i = 0; i < n; i++) {
            resultados[i] = b.get(i);
        }

        pasos.add("Sistema inicial:");
        pasos.add(formatearSistemaGauss(matriz, resultados, n));

        try {
            // Fase de eliminación hacia adelante
            for (int i = 0; i < n - 1; i++) {
                // Buscar el pivote máximo
                int filaPivote = buscarPivoteMaximo(matriz, i, n);

                // Intercambiar filas si es necesario
                if (filaPivote != i) {
                    intercambiarFilas(matriz, resultados, i, filaPivote);
                    pasos.add("Intercambio de fila " + (i + 1) + " con fila " + (filaPivote + 1));
                    pasos.add(formatearSistemaGauss(matriz, resultados, n));
                }

                // Verificar si el pivote es cero
                if (Math.abs(matriz[i][i]) < 1e-10) {
                    throw new ArithmeticException("El sistema no tiene solución única");
                }

                // Eliminación hacia adelante
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(matriz[j][i]) > 1e-10) {
                        double factor = matriz[j][i] / matriz[i][i];

                        pasos.add("Eliminando x" + (i + 1) + " de ecuación " + (j + 1)
                                + " (Factor: " + String.format("%.4f", factor) + ")");

                        for (int k = i; k < n; k++) {
                            matriz[j][k] -= factor * matriz[i][k];
                        }
                        resultados[j] -= factor * resultados[i];

                        pasos.add(formatearSistemaGauss(matriz, resultados, n));
                    }
                }
            }

            // Sustitución hacia atrás
            double[] solucion = new double[n];
            pasos.add("Sustitución hacia atrás:");

            for (int i = n - 1; i >= 0; i--) {
                double suma = 0;
                for (int j = i + 1; j < n; j++) {
                    suma += matriz[i][j] * solucion[j];
                }
                solucion[i] = (resultados[i] - suma) / matriz[i][i];

                pasos.add("x" + (i + 1) + " = " + String.format("%.6f", solucion[i]));
                vectorX.add(solucion[i]);
            }

            log.info("Solución Gauss: " + vectorX);

        } catch (Exception e) {
            log.error("Error en Gauss: " + e.getMessage());
            pasos.add("Error: " + e.getMessage());
            throw new RuntimeException("Error en eliminación Gaussiana: " + e.getMessage());
        }

        // Establecer resultados en el modelo
        modelGauss.setVectorX(vectorX);
        return modelGauss;
    }

    // Métodos auxiliares para eliminación Gaussiana
    private int buscarPivoteMaximo(double[][] matriz, int columna, int n) {
        int maxFila = columna;
        for (int i = columna + 1; i < n; i++) {
            if (Math.abs(matriz[i][columna]) > Math.abs(matriz[maxFila][columna])) {
                maxFila = i;
            }
        }
        return maxFila;
    }

    private void intercambiarFilas(double[][] matriz, double[] resultados, int fila1, int fila2) {
        double[] tempFila = matriz[fila1];
        matriz[fila1] = matriz[fila2];
        matriz[fila2] = tempFila;

        double tempResultado = resultados[fila1];
        resultados[fila1] = resultados[fila2];
        resultados[fila2] = tempResultado;
    }

    private String formatearSistemaGauss(double[][] matriz, double[] resultados, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && matriz[i][j] >= 0) {
                    sb.append(" + ");
                } else if (j > 0) {
                    sb.append(" ");
                }

                sb.append(String.format("%.4f", matriz[i][j]));
                sb.append("x").append(j + 1);
            }
            sb.append(" = ").append(String.format("%.4f", resultados[i])).append("\n");
        }
        return sb.toString();
    }

    @Override
    public GaussJordan AlgoritmoGaussJordan(GaussJordan modelGaussJordan) {
        int n = modelGaussJordan.getMN();
        ArrayList<Double> A = modelGaussJordan.getMatrizA();
        ArrayList<Double> B = modelGaussJordan.getVectorB();
        double[][] matriz = new double[n][n + 1];

        // Llenar matriz aumentada
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index++);
            }
            matriz[i][n] = B.get(i);
        }

        // Algoritmo de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Pivoteo parcial
            int max = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matriz[k][i]) > Math.abs(matriz[max][i])) {
                    max = k;
                }
            }
            double[] temp = matriz[i];
            matriz[i] = matriz[max];
            matriz[max] = temp;

            // Normalizar fila pivote
            double pivote = matriz[i][i];
            if (Math.abs(pivote) < 1e-10) {
                throw new RuntimeException("Sistema sin solución única");
            }
            for (int j = 0; j <= n; j++) {
                matriz[i][j] /= pivote;
            }

            // Eliminación en todas las filas
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matriz[k][i];
                    for (int j = 0; j <= n; j++) {
                        matriz[k][j] -= factor * matriz[i][j];
                    }
                }
            }
        }

        // Guardar solución en model
        ArrayList<Double> vectorX = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vectorX.add(matriz[i][n]);
        }
        modelGaussJordan.setVectorX(vectorX);

        return modelGaussJordan;
    }

}
