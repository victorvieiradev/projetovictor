package com.projeto.backEnd.service;

import com.projeto.backEnd.model.UsuarioModel;
import com.projeto.backEnd.repository.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarId(Long id){
        Optional<UsuarioModel> obj = usuarioRepository.findById(id);
        return obj.get();
    }

    public UsuarioModel cadastraUsuario(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel atualizaCadUsuario (UsuarioModel usuarioModel, Long id){
        UsuarioModel newUsuario = buscarId(id); //id e verificado no metodo buscar id em cima
        newUsuario.setNomeUsuario(usuarioModel.getNomeUsuario());
        //newUsuario.setCpf(usuarioModel.getCpf());
        newUsuario.setDataNascimento(usuarioModel.getDataNascimento());
        newUsuario.setEmail(newUsuario.getEmail());

        return usuarioRepository.save(newUsuario);
    }

    public void deletaUsuario(Long id){
        usuarioRepository.deleteById(id);
    }


}
