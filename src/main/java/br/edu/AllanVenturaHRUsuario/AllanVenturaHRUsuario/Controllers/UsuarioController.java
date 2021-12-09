package br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.Controllers;

import br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.Repositories.UsuarioRepository;
import br.edu.AllanVenturaHRUsuario.AllanVenturaHRUsuario.models.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping(path = {"/{id}"})
    public ResponseEntity obterPorId(@PathVariable int id){
        ResponseEntity retorno = ResponseEntity.notFound().build();
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario != null){
            retorno = ResponseEntity.ok().body(usuario);
        }
        return retorno;
    }
    
    @PutMapping
    public ResponseEntity atualizarUsuario(@RequestBody Usuario usuario){
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        if(usuario != null && usuario.getId() != null){
            
            Optional<Usuario> usuarioGravado = usuarioRepository.findById(usuario.getId());
            if(usuarioGravado != null){
            
                try{
                    Usuario usuarioAGravar = usuarioRepository.save(usuario);
                    retorno = ResponseEntity.ok().body(usuarioGravado);
                }catch(Exception e){
                }
            
            }
        }
        return retorno;
    }
    
    @PostMapping
    public ResponseEntity inserirUsuario(@RequestBody Usuario usuario){
        
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        if(usuario != null && usuario.getId() == null){
            
            Usuario usuarioInserido = usuarioRepository.save(usuario);
            retorno = ResponseEntity.ok().body(usuarioInserido);
        }
        return retorno;
    }
}
