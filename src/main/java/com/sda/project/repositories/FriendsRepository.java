package com.sda.project.repositories;

import com.sda.project.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository <Friend, Integer> {
}
