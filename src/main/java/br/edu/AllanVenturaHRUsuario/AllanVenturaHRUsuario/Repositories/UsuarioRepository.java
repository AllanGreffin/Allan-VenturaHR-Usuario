package br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.Repositories;

import br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    
    Usuario findByEmail(String email);
}
