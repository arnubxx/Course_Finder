package com.recommender.api;

import com.recommender.model.Course;
import com.recommender.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public List<Course> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
