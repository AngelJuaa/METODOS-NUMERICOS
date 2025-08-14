package mx.edu.itses.aja.MetodosNumericos.services;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.aja.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;
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
        double xi = puntofijo.getXi();
        double ea = puntofijo.getEa();
        int iterMax = puntofijo.getIteracionesMaximas();
        String gxStr = puntofijo.getGx();

        double xr;
        double errorAprox = 100.0;
        int i = 0;

        while (errorAprox > ea && i < iterMax) {
            try {
                xr = Funciones.Ecuacion(gxStr, xi);

                if (i > 0) {
                    errorAprox = Math.abs((xr - xi) / xr) * 100;
                }

                PuntoFijo paso = new PuntoFijo();
                paso.setXi(xi);
                paso.setGx(gxStr);
                paso.setXr(xr);
                paso.setEa(errorAprox);
                paso.setIteracionesMaximas(iterMax);
                respuesta.add(paso);

                xi = xr;
                i++;
            } catch (Exception e) {
                log.error("Error evaluando la función g(x): {}", e.getMessage());
                break;
            }

        }
        return respuesta;

    }

}
