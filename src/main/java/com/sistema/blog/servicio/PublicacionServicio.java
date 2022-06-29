package com.sistema.blog.servicio;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroPagina, int medidaPagina, String ordenarPor, String sortDir);

    public PublicacionDTO obtenerPorId(Long id);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);

    public void eliminarPorID(Long id);
}
