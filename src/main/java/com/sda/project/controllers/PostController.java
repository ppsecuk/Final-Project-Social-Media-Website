package com.sda.project.controllers;

import com.sda.project.entities.Comment;
import com.sda.project.entities.Post;
import com.sda.project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public String savePost(Post post) {
        postService.savePost(post);
        return  "user-profile";
    }

    @PostMapping("/{id}/comment")
    public String saveComment(@PathVariable("id") Integer postId, Comment comment) {
        postService.saveComment(postId, comment);
        return "user-profile";
    }
}
