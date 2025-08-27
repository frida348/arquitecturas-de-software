package arqui.registro_mascotas;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManejadorDeExcepciones {

    // Manejo de excepciones generalizado
    @ExceptionHandler(Exception.class)
    public String ManejoDeExcepciones(Exception ex, Model modelo) {
        modelo.addAttribute("error", ex.getMessage());
        return "/error";
    }
}
