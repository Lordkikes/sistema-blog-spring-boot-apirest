package com.sistema.blog.excepciones;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String nombreRecurso;
    private String nombreCampo;
    private Long valorCampo;

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, Long valorCampo) {
        super(String.format("%s no encontrada con : %s : '%s'",nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }
}
