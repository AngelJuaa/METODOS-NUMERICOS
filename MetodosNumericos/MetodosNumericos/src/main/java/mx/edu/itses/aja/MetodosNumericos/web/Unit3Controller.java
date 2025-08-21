package mx.edu.itses.aja.MetodosNumericos.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.EliminacionGaussiana;
import mx.edu.itses.aja.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.aja.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.aja.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaCramer;
import mx.edu.itses.aja.MetodosNumericos.services.UnidadIIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit3Controller {

    @Autowired
    private UnidadIIIService unidadIIIsrv;

    @GetMapping("/unit3")
    public String index(Model model) {
        return "unit3/index3";
    }

    ////////////////////////////////////
    @GetMapping("/unit3/formcramer")
    public String formCramer(Model model) {
        ReglaCramer modelCramer = new ReglaCramer();
        model.addAttribute("modelCramer", modelCramer);
        return "unit3/cramer/formcramer";
    }

    @PostMapping("/unit3/solvecramer")
    public String solveCramer(ReglaCramer modelCramer, Errors errores, Model model) {
        // log.info("OBJETOS: " +modelCramer);
        var solveCramer = unidadIIIsrv.AlgoritmoReglaCramer(modelCramer);
        //log.info("solucion: "+solveCramer.getVectorX());
        model.addAttribute("solveCramer", solveCramer);
        return "unit3/cramer/solvecramer";
    }

    /////////////////////////////////////////////
    @GetMapping("/unit3/formElimGauss")
    public String formGauss(Model model) {
        EliminacionGaussiana modelGauss = new EliminacionGaussiana();
        model.addAttribute("modelGauss", modelGauss);
        return "unit3/elimGauss/formElimGauss";
    }

    @PostMapping("/unit3/solveElimGauss")
    public String solveGauss(EliminacionGaussiana modelGauss, Errors errores, Model model) {
        //log.info("OBJECTOS:" + modelGauss.getMatrizA());
        ArrayList<Double> A = modelGauss.getMatrizA();
        var solveElimGauss = unidadIIIsrv.AlgoritmoEliminacionGaussiana(modelGauss);
        //log.info("Solución: " + solveGauss.getVectorX());
        //log.info("Pasos: " + solveGauss.getPasos());
        model.addAttribute("solveElimGauss", solveElimGauss);
        return "unit3/elimGauss/solveElimGauss";
    }

    ////////////////////////////////////////////////////
    @GetMapping("/unit3/formgaussjordan")
    public String formGaussJordan(Model model) {
        GaussJordan modelGaussJordan = new GaussJordan();
        model.addAttribute("modelGaussJordan", modelGaussJordan);
        return "unit3/GaussJordan/formgaussjordan";
    }

    @PostMapping("/unit3/solvegaussjordan")
    public String solveGauss(GaussJordan modelGaussJordan, Errors errores, Model model) {
        ArrayList<Double> A = modelGaussJordan.getMatrizA();
        var solveGauss = unidadIIIsrv.AlgoritmoGaussJordan(modelGaussJordan);
        model.addAttribute("solvegaussjordan", solveGauss);
        return "unit3/GaussJordan/solvegaussjordan";
    }
    ////////////////////////////////////////////

    @GetMapping("/unit3/formjacobi")
    public String formjacobi(Model model) {
        Jacobi modelJacobi = new Jacobi();
        model.addAttribute("modelJacobi", modelJacobi);
        return "unit3/jacobi/formjacobi";
    }

    @PostMapping("/unit3/solvejacobi")
    public String solvejacobi(Jacobi modelJacobi, Errors errores, Model model) {
        ArrayList<Double> A = modelJacobi.getMatrizA();
        var solveJacobi = unidadIIIsrv.AlgoritmoJacobi(modelJacobi);
        model.addAttribute("modelJacobi", modelJacobi);
        model.addAttribute("solvejacobi", solveJacobi);
        return "unit3/jacobi/solvejacobi";
    }

    //////////////////////////////////////////////
    @GetMapping("/unit3/formgaussSeidel")
    public String formGaussSeidel(Model model) {
        GaussSeidel modelGaussSeidel = new GaussSeidel();
        model.addAttribute("modelGaussSeidel", modelGaussSeidel);
        return "unit3/GaussSeidel/formgaussSeidel";
    }

    @PostMapping("/unit3/solvegaussSeidel")
    public String solveGaussSeidel(GaussSeidel modelGaussSeidel, Model model) {
        var solveGaussSeidel = unidadIIIsrv.AlgoritmoGaussSeidel(modelGaussSeidel);
        model.addAttribute("solvegaussSeidel", solveGaussSeidel);
        return "unit3/GaussSeidel/solvegaussSeidel";
    }
}
