package com.projeto.backEnd.controller;

import com.projeto.backEnd.model.UsuarioModel;
import com.projeto.backEnd.repository.UsuarioRepository;
import com.projeto.backEnd.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> buscarTudo () {
        return ResponseEntity.ok(usuarioService.buscarUsuarios());
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/pesquisaid/{id}")
    public ResponseEntity <UsuarioModel> buscaPorId (@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioService.buscarId(id)) ;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/pesquisa/{nomeUsuario}")
    public Optional<List<UsuarioModel>> tipoDeConta(String nomeUsuario){
        return Optional.ofNullable(usuarioRepository.findByNomeUsuario(nomeUsuario));
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioModel> cadastraUsuario(@RequestBody UsuarioModel usuarioModel){
        return ResponseEntity.ok(usuarioService.cadastraUsuario(usuarioModel));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PatchMapping(path = "/put/{id}")
    public ResponseEntity<UsuarioModel> atualizaUsuario(@RequestBody UsuarioModel usuarioModel, @PathVariable Long id){
        UsuarioModel usuarioNewObj = usuarioService.atualizaCadUsuario(usuarioModel, id);
        return ResponseEntity.ok().body(usuarioNewObj);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/delete/{id}")
    public void deletaConta(@PathVariable Long id){
        usuarioService.deletaUsuario(id);
    }


}
