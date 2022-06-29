package com.sistema.blog.controlador;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.servicio.PublicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @GetMapping
    public PublicacionRespuesta listarPublicaciones(
            @RequestParam(value = "numberPage", defaultValue = "0", required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaPagina) {
        return publicacionServicio.obtenerTodasLasPublicaciones(numeroPagina, medidaPagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> listarPublicacionPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(publicacionServicio.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {

        return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id) {
        PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id) {
        publicacionServicio.eliminarPorID(id);
        return new ResponseEntity<>("Publicacion Eliminada con exito...", HttpStatus.OK);
    }

}
