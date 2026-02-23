package com.desafioalura.foro_hub.domain.usuario;

import com.desafioalura.foro_hub.domain.topico.Topico;

public record DatosListadoTopico(Long id, String titulo, String mensaje, String fechaCreacion) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString());
    }
}
