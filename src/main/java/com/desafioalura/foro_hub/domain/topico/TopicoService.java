package com.desafioalura.foro_hub.domain.topico;

import com.desafioalura.foro_hub.domain.usuario.Usuario;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos, Usuario autor) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje.");
        }

        var topico = new Topico(datos, autor);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }
}