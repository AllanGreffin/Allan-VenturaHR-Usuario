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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping(path = {"/{id}"})
    public ResponseEntity obterPorId(@RequestParam int id){
        ResponseEntity retorno = ResponseEntity.notFound().build();
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            retorno = ResponseEntity.ok().body(usuario);
        }
        return retorno;
    }
    
    @GetMapping()
    public ResponseEntity<Usuario> obterPorEmail(@RequestParam String email){
        ResponseEntity retorno = ResponseEntity.notFound().build();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if(usuario != null){
            retorno = ResponseEntity.ok().body(usuario);
        }
        return retorno;
    }
    
    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario){
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        if(usuario != null && usuario.getId() != null){
            
            Usuario usuarioGravado = usuarioRepository.findById(usuario.getId()).orElse(null);
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
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario){
        
        ResponseEntity<Usuario> retorno = ResponseEntity.badRequest().build();
        if(usuario != null && usuario.getId() == null){
            
            Usuario usuarioInserido = usuarioRepository.save(usuario);
            retorno = ResponseEntity.ok().body(usuarioInserido);
        }
        return retorno;
    }
}
