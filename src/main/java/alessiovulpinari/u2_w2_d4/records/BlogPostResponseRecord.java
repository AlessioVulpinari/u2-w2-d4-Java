package alessiovulpinari.u2_w2_d4.records;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BlogPostResponseRecord(@NotNull UUID blogPostId) {
}
