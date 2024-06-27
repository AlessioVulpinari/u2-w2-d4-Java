package alessiovulpinari.u2_w2_d4.services;

import alessiovulpinari.u2_w2_d4.entities.Author;
import alessiovulpinari.u2_w2_d4.exceptions.BadRequestException;
import alessiovulpinari.u2_w2_d4.exceptions.NotFoundException;
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

    public Author save(Author newAuthor) {

        this.authorRepository.findByNameAndSurname(newAuthor.getName(), newAuthor.getSurname()).ifPresent(
                author -> {
                    throw new BadRequestException("Esiste già un autore con nome e cognome: "
                            + newAuthor.getName() + " " + newAuthor.getSurname());
                }
        );

        newAuthor.setAvatarUrl("https://ui-avatars.com/api/?name=" + newAuthor.getName() + "+"
                + newAuthor.getSurname());

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
