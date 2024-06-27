package alessiovulpinari.u2_w2_d4.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {

    private List<ObjectError> objectErrorList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> objectErrorList) {

        super("Errori nella validazione del payload");
        this.objectErrorList = objectErrorList;
    }
}
