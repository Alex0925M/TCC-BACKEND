package br.com.project.SB.NameProject.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class AtivoIsFalseException extends RuntimeException {

    private final UUID id;

    public AtivoIsFalseException(UUID id){
        super("O ID " + id + " esta com status de false.");
        this.id = id;
    }

    @Override
    public String getMessage(){
        return ("Status de ativo falso para o ID " + id);
    }

}
