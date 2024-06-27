package alessiovulpinari.u2_w2_d4.controllers;

import alessiovulpinari.u2_w2_d4.entities.BlogPost;
import alessiovulpinari.u2_w2_d4.entities.BlogPostPayload;
import alessiovulpinari.u2_w2_d4.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> getBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return blogPostService.getBlogPost(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody BlogPostPayload body) {
        return blogPostService.save(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable UUID blogPostId) {
        return blogPostService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPost findByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody BlogPostPayload body) {
        return blogPostService.findByIdAndSave(blogPostId, body);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID blogPostId) {
        blogPostService.findByIdAndDelete(blogPostId);
    }

}
