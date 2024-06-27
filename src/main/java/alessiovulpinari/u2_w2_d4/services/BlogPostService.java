package alessiovulpinari.u2_w2_d4.services;

import alessiovulpinari.u2_w2_d4.entities.BlogPost;
import alessiovulpinari.u2_w2_d4.entities.BlogPostPayload;
import alessiovulpinari.u2_w2_d4.exceptions.NotFoundException;
import alessiovulpinari.u2_w2_d4.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthorService authorService;

    public Page<BlogPost> getBlogPost(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return blogPostRepository.findAll(pageable);
    }

    public BlogPost save(BlogPostPayload newBlogPost) {

        BlogPost blogPost = new BlogPost(newBlogPost.getCategory(), newBlogPost.getTitle(), newBlogPost.getCover(), newBlogPost.getContent(),
                newBlogPost.getTimes(), authorService.findById(newBlogPost.getAuthorId()));

        return blogPostRepository.save(blogPost);
    }

    public BlogPost findById(UUID blogPostId) {
        return blogPostRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndSave(UUID blogPostId, BlogPostPayload blogPostPayload) {
        BlogPost foundBlogPost = findById(blogPostId);
        foundBlogPost.setTimes(blogPostPayload.getTimes());
        foundBlogPost.setCover(blogPostPayload.getCover());
        foundBlogPost.setContent(blogPostPayload.getContent());
        foundBlogPost.setCategory(blogPostPayload.getCategory());
        foundBlogPost.setTitle(blogPostPayload.getTitle());
        foundBlogPost.setAuthor(authorService.findById(blogPostPayload.getAuthorId()));

        return blogPostRepository.save(foundBlogPost);
    }

    public void findByIdAndDelete(UUID blogPostId) {
        BlogPost blogPost = findById(blogPostId);
        this.blogPostRepository.delete(blogPost);
    }

}
