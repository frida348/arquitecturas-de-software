package arqui.registro_mascotas.BD;

import org.springframework.data.jpa.repository.JpaRepository;
import arqui.registro_mascotas.Modelo.Mascota;

/*
 * El repositorio es la clase encargada de permitir que realicemos cambios en la base de datos sin necesidad de escribir nuestras "querys" de forma manual
 * JpaRepository contiene los métodos necesarios para construir un crud, por lo cual no es necesario declarar ningún método nosotros mismos
 */

public interface RepositorioMascotas extends JpaRepository<Mascota, Integer>{
    
}
