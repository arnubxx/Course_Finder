package com.recommender.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String difficulty;
    private String courseUrl;

    public Course() { }

    public Course(String title, String description, String category, String difficulty, String courseUrl) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.courseUrl = courseUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getCourseUrl() { return courseUrl; }
    public void setCourseUrl(String courseUrl) { this.courseUrl = courseUrl; }
}
