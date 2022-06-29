package com.sistema.blog.servicio;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.PublicacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {

        Publicacion publicacion = mapearEntidad(publicacionDTO);
        Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

        return mapearDTO(nuevaPublicacion);

    }

    @Override
    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {

        List<Publicacion> publicaciones = publicacionRepositorio.findAll();

        return publicaciones.stream().map(this::mapearDTO).collect(Collectors.toList());

    }

    @Override
    public PublicacionDTO obtenerPorId(Long id) {

        Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() ->new ResourceNotFoundException("Publicacion", "id", id));

        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {

        Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() ->new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);

        return mapearDTO(publicacionActualizada);
    }

    //Convierte Entidad a DTO
    private PublicacionDTO mapearDTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();

        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());
        return publicacionDTO;
    }

    //Convierte DTO a Entidad
    private Publicacion mapearEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        return publicacion;
    }
}
