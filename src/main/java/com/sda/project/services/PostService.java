package com.sda.project.services;

import com.sda.project.entities.Comment;
import com.sda.project.entities.Like;
import com.sda.project.entities.Post;
import com.sda.project.entities.User;
import com.sda.project.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void saveComment(Integer postId, Comment comment, User user) {
        Post post = getPostById(postId);
        comment.setDate(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);
        post.getComments().add(comment);
        savePost(post);
    }

    public void saveLike(Integer postId, User user){
        Post post = getPostById(postId);
        boolean alreadyLiked = false;
        for (Like l : post.getLikes()) {
            if (l.getUser().getUserId() == user.getUserId()) {
                post.getLikes().remove(l);
                alreadyLiked = true;
                break;
            }
        }

        if(!alreadyLiked) {
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            post.getLikes().add(like);
        }

        savePost(post);
    }
}
