package pl.adamsalata.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.adamsalata.comment.model.entity.Comment;
import pl.adamsalata.comment.model.repository.CommentRepository;

import java.util.Collection;

@RestController
@RequestMapping("/comment")
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public boolean add(@RequestBody Comment comment){
            commentRepository.save(comment);
            return true;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @RequestMapping(value = "/findActive", method = RequestMethod.GET)
    public Collection<Comment> findActive() {
        return commentRepository.findByActive(true);
    }

    @RequestMapping(value = "/deactivate/{id}", method = RequestMethod.DELETE)
    public void deactivate(@PathVariable long id) {
        Comment comment = commentRepository.findOne(id);
        comment.setActive(false);
        commentRepository.save(comment);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        commentRepository.delete(commentRepository.findOne(id));
    }
}
// TODO: think about this version with token authentication
// TODO: remove it or activate it later on
//    @Autowired
//    private SessionRepository sessionRepository;
//    @RequestMapping(value = "/add/{token}", method = RequestMethod.PUT)
//    public boolean add(@RequestBody Comment comment, @PathVariable String token) {
//        if (sessionRepository.findByToken(token) != null) {
//        commentRepository.save(comment);
//        return true;
//        }
//        return false;
//    }