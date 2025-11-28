# AI Course Recommender (Spring Boot)

Minimal Spring Boot application demonstrating a content-based course recommendation engine using TF-IDF implemented in Java.

## Run locally

1. Build & run with Maven:

```bash
mvn spring-boot:run
```

2. Open `http://localhost:8080`

## File overview
- `CourseService` reads CSV dataset
- `TfidfVectorizer` converts text to numeric vectors
- `CosineSimilarity` ranks similarity
- `RecommendationService` returns top K courses
- Thymeleaf templates provide UI

## Next steps
- Replace TF-IDF with embeddings (BERT) using Python or Java wrappers
- Persist courses in a database
- Add user profiles and feedback loop
