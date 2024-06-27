package alessiovulpinari.u2_w2_d4.services;

import alessiovulpinari.u2_w2_d4.entities.Author;
import alessiovulpinari.u2_w2_d4.exceptions.BadRequestException;
import alessiovulpinari.u2_w2_d4.exceptions.NotFoundException;
import alessiovulpinari.u2_w2_d4.records.AuthorRecord;
import alessiovulpinari.u2_w2_d4.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Page<Author> getAuthors(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return authorRepository.findAll(pageable);
    }

    public Author save(AuthorRecord record) {

        this.authorRepository.findByNameAndSurname(record.name(), record.surname()).ifPresent(
                author -> {
                    throw new BadRequestException("Esiste già un autore con nome e cognome: "
                            + record.name() + " " + record.surname());
                }
        );

        Author newAuthor = new Author(record.name(), record.surname(), record.email(), record.dateOfBirth());

        newAuthor.setAvatarUrl("https://ui-avatars.com/api/?name=" + record.name() + "+"
                + record.surname());

        return authorRepository.save(newAuthor);
    }

    public Author findById(UUID authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndSave(UUID authorId, Author newAuthor) {

        Author foundAuthor = findById(authorId);
        foundAuthor.setName(newAuthor.getName());
        foundAuthor.setSurname(newAuthor.getSurname());
        foundAuthor.setEmail(newAuthor.getEmail());
        foundAuthor.setDateOfBirth(newAuthor.getDateOfBirth());
        foundAuthor.setAvatarUrl("https://ui-avatars.com/api/?name=" + newAuthor.getName() + "+" + newAuthor.getSurname());
        return this.authorRepository.save(foundAuthor);
    }

    public void findByIdAndDelete(UUID authorId) {
        Author foundAuthor = findById(authorId);
        this.authorRepository.delete(foundAuthor);
    }
}
