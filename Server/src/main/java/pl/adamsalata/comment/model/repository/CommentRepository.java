package pl.adamsalata.comment.model.repository;



import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.adamsalata.comment.model.entity.Comment;

import java.util.Collection;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    public Collection<Comment> findByActive(boolean active);
}
