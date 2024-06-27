package alessiovulpinari.u2_w2_d4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "autori")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "id_autore", nullable = false)
    private UUID authorId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cognome", nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_di_nascita", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String avatarUrl;

    public Author(String name, String surname, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.setAvatarUrl("https://ui-avatars.com/api/?name=" + this.getName() + this.getSurname());
    }
}
