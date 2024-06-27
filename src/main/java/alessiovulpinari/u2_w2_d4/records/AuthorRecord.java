package alessiovulpinari.u2_w2_d4.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRecord(

        @NotBlank(message = "Il nome non puà essere un campo vuoto!")
        @Size(min = 5, max = 15, message = "Il nome deve essere compreso tra i 4 e i 15 caratteri!")
        String name,

        @NotBlank(message = "Il cognome non puà essere un campo vuoto!")
        @Size(min = 5, max = 15, message = "Il cognome deve essere compreso tra i 3 e i 20 caratteri!")
        String surname,

        @NotBlank(message = "L'email non puà essere un campo vuoto!")
        @Email(message = "Formato mail errato!")
        String email,

        @NotEmpty
        LocalDate dateOfBirth) {
}
