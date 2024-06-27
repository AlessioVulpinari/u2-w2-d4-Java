package alessiovulpinari.u2_w2_d4.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record BlogPostRecord(
        @NotBlank(message = "La categoria non puà essere un campo vuoto!")
        @Size(min = 5, max = 15, message = "La categoria deve essere compreso tra i 4 e i 25 caratteri!")
        String category,
        @NotBlank(message = "Il titolo non puà essere un campo vuoto!")
        @Size(min = 5, max = 15, message = "Il titolo deve essere compreso tra i 2 e i 50 caratteri!")
        String title,
        @NotBlank(message = "Il contenuto non puà essere un campo vuoto!")
        String content,
        @NotBlank(message = "La cover non puà essere un campo vuoto!")
        String cover,
        @NotNull
        @Min(1)
        int times,
        @NotNull
        UUID authorId) {
}
