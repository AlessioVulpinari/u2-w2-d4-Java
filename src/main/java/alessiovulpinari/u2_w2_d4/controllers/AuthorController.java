package alessiovulpinari.u2_w2_d4.controllers;

import alessiovulpinari.u2_w2_d4.entities.Author;
import alessiovulpinari.u2_w2_d4.exceptions.BadRequestException;
import alessiovulpinari.u2_w2_d4.records.AuthorRecord;
import alessiovulpinari.u2_w2_d4.records.AuthorResponseRecord;
import alessiovulpinari.u2_w2_d4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public AuthorResponseRecord saveAuthor(@RequestBody @Validated AuthorRecord body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors());
        return new AuthorResponseRecord(authorService.save(body).getAuthorId());
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
