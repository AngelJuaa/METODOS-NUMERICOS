package mx.edu.itses.aja.MetodosNumericos.web;


import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.aja.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.aja.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.aja.MetodosNumericos.domain.Secante;
import mx.edu.itses.aja.MetodosNumericos.services.Funciones;
import mx.edu.itses.aja.MetodosNumericos.services.UnidadllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit2Controller {
    @Autowired
    private UnidadllService bisectionservice;
     @Autowired
    private UnidadllService reglafalsaservice;
     @Autowired
    private UnidadllService puntofijoservice;
     @Autowired
    private UnidadllService NewtonRaphsonservice;
     @Autowired
    private UnidadllService solvesecanteservice;

    @GetMapping("unit2/formbisection")
    public String formBiseccion(Model model) {

        Biseccion bisection = new Biseccion();
        
        model.addAttribute("bisection",bisection);
        return "unit2/bisection/formbisection";
    }
    
    @PostMapping("/unit2/solvebisection")
    public String solvebisection(Biseccion bisection,Model model){
        
        //double valorFX = Funciones.Ecuacion(bisection.getFX(), 2);
        //log.info("Valor de FX: "+ valorFX);
        
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);
        log.info("Arreglo"+solveBisection);
        
        
        model.addAttribute("solvebisection", solveBisection);
        return "/unit2/bisection/solvebisection";
    }
    
    @GetMapping("unit2/formreglafalsa")
    public String formreglafalsa(Model model) {

        ReglaFalsa reglafalsa = new ReglaFalsa();
        
        model.addAttribute("reglafalsa",reglafalsa);
        return "unit2/reglafalsa/formreglafalsa";
    }
    
    @PostMapping("/unit2/solvereglafalsa")
    public String solvereglafalsa(ReglaFalsa reglafalsa,Model model){
        
        //double valorFX = Funciones.Ecuacion(bisection.getFX(), 2);
        //log.info("Valor de FX: "+ valorFX);
        
        var solveReglaFalsa = reglafalsaservice.AlgoritmoReglaFalsa(reglafalsa);
        log.info("Arreglo"+solveReglaFalsa);
        
        
        model.addAttribute("solveReglaFalsa", solveReglaFalsa);
        return "/unit2/reglafalsa/solveReglaFalsa";
    }
    
    @GetMapping("unit2/formpuntofijo")
    public String formpuntofijo(Model model) {

        PuntoFijo puntofijo = new PuntoFijo();
        
        model.addAttribute("puntofijo",puntofijo);
        return "unit2/puntofijo/formpuntofijo";
    }
    
    @PostMapping("/unit2/solvepuntofijo")
    public String solvepuntofijo(PuntoFijo puntofijo ,Model model){
        
        //double valorFX = Funciones.Ecuacion(bisection.getFX(), 2);
        //log.info("Valor de FX: "+ valorFX);
        
        var solvePuntoFijo = puntofijoservice.AlgoritmoPuntoFijo(puntofijo);
        log.info("Arreglo"+solvePuntoFijo);
        
        
        model.addAttribute("solvepuntofijo", solvePuntoFijo);
        return "/unit2/puntofijo/solvepuntofijo";
    }
    @GetMapping("unit2/formnewtonphs")
    public String formnewtonphs(Model model) {

        NewtonRaphson newtonraphson = new NewtonRaphson();
        
        model.addAttribute("newtonraphson",newtonraphson);
        return "unit2/newtonraphson/formnewtonphs";
    }
    
    @PostMapping("/unit2/solvenewtonphs")
    public String solvenewtonphs(NewtonRaphson newtonraphson ,Model model){
        
        
        var solveNewtonRaphson = NewtonRaphsonservice.AlgoritmoNewtonRaphson(newtonraphson);
        log.info("Arreglo"+solveNewtonRaphson);
        
        
        model.addAttribute("solvenewtonphs", solveNewtonRaphson);
        return "/unit2/newtonraphson/solvenewtonphs";
    }
    @GetMapping("unit2/formsecante")
    public String formsecante(Model model) {

        Secante secante = new Secante();
        
        model.addAttribute("secante",secante);
        return "unit2/secante/formsecante";
    }
    
    @PostMapping("/unit2/solvesecante")
    public String solvesecante(Secante secante ,Model model){
        
        
        var solvesecante = solvesecanteservice.AlgoritmoSecante(secante);
        log.info("Arreglo"+solvesecante);
        
        
        model.addAttribute("solvesecante", solvesecante);
        return "/unit2/secante/solvesecante";
    }
    
}
