package com.sda.project.repositories;

import com.sda.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUserUserId(Integer userId);
}
