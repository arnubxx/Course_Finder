package com.recommender.controller;

import com.recommender.service.RecommendationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RecommendationController {

    private final RecommendationService recService;

    public RecommendationController(RecommendationService recService) {
        this.recService = recService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/recommend")
    public String recommend(@RequestParam(name = "interests") String interests,
                            @RequestParam(name = "difficulty", defaultValue = "any") String difficulty,
                            Model model) {

        List<Map<String, Object>> results = recService.recommend(interests, difficulty, 5);
        model.addAttribute("results", results);
        model.addAttribute("interests", interests);
        return "results";
    }

    @GetMapping("/recommend")
    public String recommendGet(Model model) {
        