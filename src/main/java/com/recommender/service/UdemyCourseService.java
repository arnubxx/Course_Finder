package com.recommender.service;

import com.recommender.model.Course;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@Service
public class UdemyCourseService {
    private static final String UDEMY_API_URL = "https://www.udemy.com/api-2.0/courses/";
    private static final String UDEMY_CLIENT_ID = "YOUR_CLIENT_ID"; // Replace with your Udemy client ID
    private static final String UDEMY_CLIENT_SECRET = "YOUR_CLIENT_SECRET"; // Replace with your Udemy client secret

    public List<Course> searchCourses(String keyword) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(UDEMY_API_URL)
                .queryParam("search", keyword)
                .queryParam("page_size", 5)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(UDEMY_CLIENT_ID, UDEMY_CLIENT_SECRET);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        List<Course> result = new ArrayList<>();
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Map<String, Object>> courses = (List<Map<String, Object>>) response.getBody().get("results");
            for (Map<String, Object> c : courses) {
                String title = (String) c.get("title");
                String description = (String) c.get("headline");
                String category = "Udemy";
                String difficulty = ""; // Udemy API may provide this in more detail
                String courseUrl = "https://www.udemy.com" + c.get("url");
                result.add(new Course(title, description, category, difficulty, courseUrl));
            }
        }
        return result;
    }
}
