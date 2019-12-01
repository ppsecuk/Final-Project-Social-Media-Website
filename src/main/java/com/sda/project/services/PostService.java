package com.sda.project.services;

import com.sda.project.entities.Comment;
import com.sda.project.entities.Post;
import com.sda.project.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepository;


    public List<Post> getPostsByUserId(Integer userId) {
        return postsRepository.findAllByUserUserId(userId);
    }

    /*public LocalDateTime formatDate(LocalDateTime givenDate){
    }*/

    public void savePost(Post post) {
        postsRepository.save(post);
    }

    public Post getPostById(Integer postId) {
        return  postsRepository.findById(postId).get();
    }

    public void saveComment(Integer postId, Comment comment) {
        Post post = getPostById(postId);
        post.getComments().add(comment);
        postsRepository.save(post);
    }
}
