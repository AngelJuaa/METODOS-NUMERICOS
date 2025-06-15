package mx.edu.itses.aja.MetodosNumericos.web;


import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.Biseccion;
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
    
}
