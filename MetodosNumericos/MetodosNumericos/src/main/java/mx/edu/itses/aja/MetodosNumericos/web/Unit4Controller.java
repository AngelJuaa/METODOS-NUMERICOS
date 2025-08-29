package mx.edu.itses.aja.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.aja.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.aja.MetodosNumericos.domain.Lagrange;
import mx.edu.itses.aja.MetodosNumericos.services.UnidadIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit4Controller {
    
@Autowired
    private  UnidadIVService unidadIVsrv;

   
    @GetMapping("/unitIV")
    public String index(Model model) {
        return "unitIV/index4";
    }

    // Formulario Diferencias Divididas de Newton
    @GetMapping("/unitIV/ddNewton/formDDNewton")
    public String formDDNewton(Model model) {
        DDNewton modelDDNewton = new DDNewton();
        model.addAttribute("modelDDNewton", modelDDNewton);
        return "/unitIV/ddNewton/formDDNewton";
    }

    
    // Resolver Newton
    @PostMapping("/unitIV/ddNewton/solveDDNewton")
    public String solveDDNewton(DDNewton modelDDNewton, Errors errores, Model model) {
        var solveDDNewton = unidadIVsrv.AlgoritmoDDNewton(modelDDNewton);
        model.addAttribute("solveDDNewton", solveDDNewton);
        return "unitIV/ddNewton/solveDDNewton";
    }
    
// Formulario Lagrange
    @GetMapping("/unitIV/lagrange/formLagrange")
    public String formLagrange(Model model) {
        Lagrange modelLagrange = new Lagrange();
        model.addAttribute("modelLagrange", modelLagrange);
        return "/unitIV/lagrange/formLagrange";
    }

    // Resolver Lagrange
    @PostMapping("/unitIV/lagrange/solveLagrange")
    public String solveLagrange(Lagrange modelLagrange, Errors errores, Model model) {
        var solveLagrange = unidadIVsrv.AlgoritmoLagrange(modelLagrange);
        model.addAttribute("resultLagrange", solveLagrange);
        return "/unitIV/lagrange/solveLagrange";
    }
}
