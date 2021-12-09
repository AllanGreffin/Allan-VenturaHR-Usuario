package br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.Repositories;

import br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.models.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    
    Optional<Usuario> findByEmail(String email);
}
