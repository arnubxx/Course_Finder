package com.recommender.service;

import com.recommender.model.Course;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@Service
public class UdemyCourseService {
    private static final String UDEMY_API_URL = "https: