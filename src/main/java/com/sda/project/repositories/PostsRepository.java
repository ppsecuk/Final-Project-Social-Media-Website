package com.sda.project.repositories;

import com.sda.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Integer> {
}
