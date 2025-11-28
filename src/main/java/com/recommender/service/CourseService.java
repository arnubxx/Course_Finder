package com.recommender.service;

import com.recommender.model.Course;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void loadCourses() {
        try {
            InputStream is = getClass().getResourceAsStream("/data/courses.csv");
            if (is == null) {
                System.err.println("courses.csv not found in resources/data");
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] arr = parseCsvLine(line);
                if (arr.length < 6) continue;
                Course c = new Course(
                    arr[1].trim(), 