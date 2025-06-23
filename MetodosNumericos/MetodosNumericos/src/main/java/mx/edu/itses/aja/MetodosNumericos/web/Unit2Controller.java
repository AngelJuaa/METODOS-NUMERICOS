package mx.edu.itses.aja.MetodosNumericos.web;


import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.aja.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.aja.MetodosNumericos.domain.ReglaFalsa;
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
    
}
