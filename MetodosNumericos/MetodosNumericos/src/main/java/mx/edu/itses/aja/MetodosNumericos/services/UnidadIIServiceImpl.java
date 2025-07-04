package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.aja.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.aja.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.aja.MetodosNumericos.domain.Secante;
import mx.edu.itses.aja.MetodosNumericos.domain.SecanteModificado;
import mx.edu.itses.aja.MetodosNumericos.services.Funciones;
import mx.edu.itses.aja.MetodosNumericos.services.UnidadllService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadllService {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion) {
        ArrayList<Biseccion> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = biseccion.getXL();
        XU = biseccion.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
        FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
        if (FXL * FXU < 0) {
            for (int i = 1; i <= biseccion.getIteracionesMaximas(); i++) {
                XRn = (XL + XU) / 2;
                FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
                FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
                FXR = Funciones.Ecuacion(biseccion.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                Biseccion renglon = new Biseccion();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= biseccion.getEa()) {
                    break;
                }
            }
        } else {
            Biseccion renglon = new Biseccion();
            // renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;
    }

    @Override
    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa) {
        ArrayList<ReglaFalsa> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = reglafalsa.getXL();
        XU = reglafalsa.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(reglafalsa.getFX(), XL);
        FXU = Funciones.Ecuacion(reglafalsa.getFX(), XU);

        if (FXL * FXU < 0) {
            for (int i = 1; i <= reglafalsa.getIteracionesMaximas(); i++) {
                XRn = XU - (FXU * (XL - XU)) / (FXL - FXU);
                FXL = Funciones.Ecuacion(reglafalsa.getFX(), XL);
                FXU = Funciones.Ecuacion(reglafalsa.getFX(), XU);
                FXR = Funciones.Ecuacion(reglafalsa.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                ReglaFalsa renglon = new ReglaFalsa();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= reglafalsa.getEa()) {
                    break;
                }
            }
        } else {
            ReglaFalsa renglon = new ReglaFalsa();
            // renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;

    }

    @Override
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo) {
        ArrayList<PuntoFijo> respuesta = new ArrayList<>();
        double xi, xr, Ea;
        double errorAprox = 100;
        int iterMax = puntofijo.getIteracionesMaximas();

        xi = puntofijo.getXi();
        Ea = puntofijo.getEa();

        for (int i = 1; i <= iterMax; i++) {
            xr = Funciones.EvaluarG(puntofijo.getGx(), xi);  // g(xi)

            if (i != 1) {
                errorAprox = Funciones.ErrorRelativo(xr, xi);
                if (Double.isNaN(errorAprox)) {
                    log.error("Error relativo inválido en iteración {}. xi={}, xr={}", i, xi, xr);
                    break;
                }
            }

            PuntoFijo paso = new PuntoFijo();
            paso.setXi(xi);
            paso.setXr(xr);
            paso.setEa(errorAprox);
            paso.setGx(puntofijo.getGx());
            paso.setIteracionesMaximas(iterMax);
            respuesta.add(paso);

            if (errorAprox <= Ea) {
                break;
            }

            xi = xr;
        }

        return respuesta;
    }

    @Override
    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonraphson) {
        ArrayList<NewtonRaphson> respuesta = new ArrayList<>();

        double XI = newtonraphson.getXI();
        //double XR = 0;
        double Ea = 100;
        String FX = newtonraphson.getFX();

        for (int i = 1; i <= newtonraphson.getIteracionesMaximas(); i++) {
            double FXi = Funciones.Ecuacion(FX, XI);
            double FDXi = Funciones.Derivada(FX, XI);

            if (FDXi == 0) {
                System.err.println("Error: Derivada es cero en XI = " + XI + ". No se puede continuar.");
                break;
            }
            double XR = XI - (FXi / FDXi);

            if (i != 1) {
                Ea = Funciones.ErrorRelativo(XR, XI);
            }

            NewtonRaphson renglon = new NewtonRaphson();
            renglon.setXI(XI);
            renglon.setFX(String.valueOf(FXi));
            renglon.setFDX(String.valueOf(FDXi));
            //renglon.setFDX(FDX);
            //renglon.setFX(FX);
            renglon.setXR(XR);
            renglon.setEa(Ea);
            renglon.setIteracion(i);
            respuesta.add(renglon);
            if (Ea <= newtonraphson.getEa()) {
                break;
            }

            XI = XR;
        }
        return respuesta;
    }

    @Override
    public ArrayList<Secante> AlgoritmoSecante(Secante secante) {
        ArrayList<Secante> resultado = new ArrayList<>();

        double xi0 = secante.getXiAnterior();
        System.out.println("Valor xi-1 recibido: " + secante.getXiAnterior());

        double xi1 = secante.getXi();
        double ea = 100;
        int iteraciones = secante.getIteracionesMaximas();
        double tolerancia = secante.getTolerancia();
        String funcion = secante.getFuncion();

        for (int i = 1; i <= iteraciones; i++) {
            double fx0 = Funciones.Ecuacion(funcion, xi0);
            double fx1 = Funciones.Ecuacion(funcion, xi1);

            if ((fx1 - fx0) == 0) {
                break;
            }

            double numerador = fx1 * (xi1 - xi0);
            double denominador = fx1 - fx0;
            double xr = xi1 - numerador / denominador;

            if (i > 1) {
                ea = Funciones.ErrorRelativo(xr, xi1);
            }

            Secante renglon = new Secante();
            renglon.setIteracion(i);
            renglon.setXiAnterior(xi0);
            renglon.setXi(xi1);
            renglon.setFxAnterior(fx0);
            renglon.setFx(fx1);
            renglon.setXr(xr);
            renglon.setEa(ea);
            renglon.setTolerancia(tolerancia);
            renglon.getIteracionesMaximas();
            renglon.setFuncion(funcion);

            resultado.add(renglon);

            if (ea <= tolerancia) {
                break;
            }

            xi0 = xi1;
            xi1 = xr;
        }
        return resultado;
    }

    @Override
    public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado secantemodificado) {
    ArrayList<SecanteModificado> resultado = new ArrayList<>();

    double xi = secantemodificado.getXi();
    double delta = secantemodificado.getDelta();
    double ea = 100;
    int iteracionesMaximas = secantemodificado.getIteracionesMaximas();
    double tolerancia = secantemodificado.getTolerancia();
    String funcion = secantemodificado.getFuncion();

    System.out.println("Valor xi inicial recibido: " + xi);
    System.out.println("Valor delta recibido: " + delta);

    for (int i = 1; i <= iteracionesMaximas; i++) {
        double fxi = Funciones.Ecuacion(funcion, xi);
        double fxiDelta = Funciones.Ecuacion(funcion, xi + delta * xi);

        double denominador = (fxiDelta - fxi) / (delta * xi);

        if (denominador == 0 || Double.isInfinite(fxi) || Double.isNaN(fxi) ||
            Double.isInfinite(fxiDelta) || Double.isNaN(fxiDelta)) {
            System.out.println("División por cero o valor de función inválido. Deteniendo iteraciones.");
            break;
        }

        double xr = xi - (fxi / denominador);

        if (i > 1) {
            ea = Funciones.ErrorRelativo(xr, xi);
        }

        SecanteModificado renglon = new SecanteModificado();
        renglon.setIteracion(i);
        renglon.setXi(xi);
        renglon.setXiAnterior(0.0);
        renglon.setFx(fxi);
        renglon.setFxAnterior(fxiDelta);
        renglon.setXr(xr);
        renglon.setEa(ea);
        renglon.setTolerancia(tolerancia);
        renglon.setIteracionesMaximas(iteracionesMaximas);
        renglon.setFuncion(funcion);
        renglon.setDelta(delta);

        resultado.add(renglon);

        if (ea <= tolerancia) {
            break;
        }

        xi = xr;
    }

    return resultado;
}

}
