package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Data
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Title cannot be empty")
    @Column(name = "titles")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Column(name = "contents")
    private String content;
}

