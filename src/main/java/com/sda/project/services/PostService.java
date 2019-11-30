package com.sda.project.services;

import com.sda.project.entities.Post;
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
}
