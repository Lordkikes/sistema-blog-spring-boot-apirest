package com.sistema.blog.servicio;

import com.sistema.blog.dto.PublicacionDTO;

import java.util.List;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public List<PublicacionDTO> obtenerTodasLasPublicaciones();
}
