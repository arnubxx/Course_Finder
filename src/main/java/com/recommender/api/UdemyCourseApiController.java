package com.recommender.api;

import com.recommender.model.Course;
import com.recommender.service.UdemyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UdemyCourseApiController {
    @Autowired
    private UdemyCourseService udemyCourseService;

    @GetMapping("/api/udemy-courses")
    public List<Course> getUdemyCourses(@RequestParam String keyword) {
        return udemyCourseService.searchCourses(keyword);
    }
}
