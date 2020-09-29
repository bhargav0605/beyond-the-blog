package demo.beyondtheblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.beyondtheblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
