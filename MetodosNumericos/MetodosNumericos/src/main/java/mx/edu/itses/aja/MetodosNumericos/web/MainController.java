
package mx.edu.itses.aja.MetodosNumericos.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    
    //pagina que vamos a ir
    public String inicio(Model model){
        int i = 1;
        log.info("mensaje de salida: {} ",i);
       
        model.addAttribute("Valori",i);
        return "index";
    }
    
}
