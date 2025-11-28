package com.recommender.service;

import com.recommender.model.Course;
import com.recommender.ml.CosineSimilarity;
import com.recommender.ml.TfidfVectorizer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final CourseService courseService;

    public RecommendationService(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<Map<String, Object>> recommend(String interests, String difficulty, int topK) {
        List<Course> courses = courseService.getAll();
        List<String> docs = courses.stream().map(c -> c.getDescription() == null ? "" : c.getDescription()).collect(Collectors.toList());
        TfidfVectorizer vectorizer = new TfidfVectorizer(docs);
        double[] userVec = vectorizer.transform(interests == null ? "" : interests);

        Map<Course, Double> scored = new HashMap<>();

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            if (difficulty != null && !difficulty.equalsIgnoreCase("any") && !difficulty.isBlank()) {
                if (!c.getDifficulty().equalsIgnoreCase(difficulty)) continue;
            }
            double[] courseVec = vectorizer.transformFromDocIndex(i);
            double sim = CosineSimilarity.compute(userVec, courseVec);
            scored.put(c, sim);
        }

        return scored.entrySet().stream()
                .sorted(Map.Entry.<Course, Double>comparingByValue().reversed())
                .limit(topK)
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("course", e.getKey());
                    m.put("score", e.getValue());
                    return m;
                }).collect(Collectors.toList());
    }
}
