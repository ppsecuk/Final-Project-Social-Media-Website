package com.sda.project.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotNull
//    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @NotBlank
    private String content;

    private Integer likes;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

}