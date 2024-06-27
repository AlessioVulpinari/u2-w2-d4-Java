package alessiovulpinari.u2_w2_d4.controllers;

import alessiovulpinari.u2_w2_d4.entities.Author;
import alessiovulpinari.u2_w2_d4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return authorService.getAuthors(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author body) {
        return authorService.save(body);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId) {
        return authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable UUID authorId, @RequestBody Author body) {
        return authorService.findByIdAndSave(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID authorId) {
        authorService.findByIdAndDelete(authorId);
    }

}
