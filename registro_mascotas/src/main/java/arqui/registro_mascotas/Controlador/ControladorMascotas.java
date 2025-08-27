package arqui.registro_mascotas.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arqui.registro_mascotas.BD.RepositorioMascotas;
import arqui.registro_mascotas.Modelo.Mascota;

/*
 * El controlador es la clase que se encarga de asignar peticiones http a los diferentes urls
 * ControladorMascotas implementa el crud para añadir mascotas a la base de datos
 */

@Controller
@RequestMapping("/mascotas")
public class ControladorMascotas {
    @Autowired
    private RepositorioMascotas repo;

    // Mostrar lista de mascotas
    @GetMapping("")
    public String MostrarListaDeMascotas(Model modelo) 
    {
        var mascotas = repo.findAll();
        modelo.addAttribute("mascotas", mascotas);
        return "mascotas/index";
    }

    // Mostrar formulario para agregar una nueva mascota
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevaMascota(Model modelo)
    {
        modelo.addAttribute("mascota", new Mascota());
        return "mascotas/nueva_mascota";
    }

    // Guardar una nueva mascota
    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute("mascota") Mascota mascota)
    {
        repo.save(mascota);
        return "redirect:/mascotas";
    }

    // Mostrar formulario para editar una mascota existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarMascota(@PathVariable("id") int id, Model modelo) {
        var mascota = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de mascota inexistente"));
        modelo.addAttribute("mascota", mascota);
        return "mascotas/editar_mascota";
    }

    // Actualizar una mascota existente
    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@PathVariable("id") int id, @RequestParam String nombre, @RequestParam int edad, @RequestParam String urlImagen)
    {
        Mascota mascota = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de mascota inexistente"));
        mascota.setNombre(nombre);
        mascota.setEdad(edad);
        mascota.setUrlImagen(urlImagen);

        repo.save(mascota);

        return "redirect:/mascotas";
    }

    // Eliminar una mascota
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") int id) {
        repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de mascota inexistente"));
        repo.deleteById(id);
        return "redirect:/mascotas";
    }
    
    // Eliminar multiples mascotas
    @PostMapping("/eliminar-multiples")
    public String eliminarMultiples(@RequestParam("mascotasIds") List<Integer> mascotasIds) {
        if (mascotasIds != null && !mascotasIds.isEmpty()) {
            repo.deleteAllById(mascotasIds);
        }
        return "redirect:/mascotas";
    }
}
