package com.sistema.blog.controlador;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.servicio.PublicacionServicio;
import com.sistema.blog.utilerias.AppConstantes;
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
            @RequestParam(value = "numberPage", defaultValue = AppConstantes.NUMERO_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaPagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return publicacionServicio.obtenerTodasLasPublicaciones(numeroPagina, medidaPagina, ordenarPor, sortDir);
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
