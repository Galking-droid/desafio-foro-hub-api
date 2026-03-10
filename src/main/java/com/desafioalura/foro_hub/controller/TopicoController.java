package com.desafioalura.foro_hub.controller;

import com.desafioalura.foro_hub.domain.topico.*;
import com.desafioalura.foro_hub.domain.usuario.DatosListadoTopico;
import com.desafioalura.foro_hub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                    @AuthenticationPrincipal Usuario autor,
                                    UriComponentsBuilder uriComponentsBuilder) {
        var respuesta = topicoService.registrar(datos, autor);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginacion) {
        var pagina = topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizar, @AuthenticationPrincipal Usuario usuarioAutenticado) {
        var topico = topicoRepository.getReferenceById(id);

        if (!topico.getAutor().getId().equals(usuarioAutenticado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para modificar un tópico que no te pertenece.");
        }

        topico.actualizarDatos(datosActualizar);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioAutenticado) {
        var topico = topicoRepository.getReferenceById(id);

        if (!topico.getAutor().getId().equals(usuarioAutenticado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para eliminar un tópico que no te pertenece.");
        }

        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}