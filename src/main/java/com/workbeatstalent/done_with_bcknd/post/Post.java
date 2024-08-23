package com.workbeatstalent.done_with_bcknd.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private BigDecimal price;

    @ElementCollection
    @CollectionTable(name = "post_images")
    private List<Image> images = new ArrayList<>();

    @Enumerated(value = EnumType.ORDINAL)
    private Category category;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Embedded
    private Location location;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Post(String title, BigDecimal price, List<Image> images, Category category, Author author, Location location) {
        this.title = title;
        this.price = price;
        this.images = images;
        this.category = category;
        this.author = author;
        this.location = location;
    }
}
