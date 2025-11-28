package com.recommender.ml;

import java.util.*;
import java.util.regex.Pattern;

public class TfidfVectorizer {

    private final List<String> documents;
    private final Map<String, Integer> vocabIndex = new LinkedHashMap<>();
    private final List<Map<String, Integer>> termFrequencies = new ArrayList<>();
    private final Map<String, Double> idf = new HashMap<>();

    private static final Pattern TOKEN_SPLIT = Pattern.compile("\\W+");

    public TfidfVectorizer(List<String> docs) {
        this.documents = docs;
        build();
    }

    private void build() {
        for (String doc : documents) {
            Map<String, Integer> tf = new HashMap<>();
            String[] tokens = TOKEN_SPLIT.split(doc.toLowerCase());
            for (String t : tokens) {
                if (t.isBlank()) continue;
                tf.put(t, tf.getOrDefault(t, 0) + 1);
                if (!vocabIndex.containsKey(t)) {
                    vocabIndex.put(t, vocabIndex.size());
                }
            }
            termFrequencies.add(tf);
        }

        int nDocs = documents.size();
        for (String term : vocabIndex.keySet()) {
            int docsWithTerm = 0;
            for (Map<String, Integer> tf : termFrequencies) {
                if (tf.containsKey(term)) docsWithTerm++;
            }
            double value = Math.log((double) nDocs / (1 + docsWithTerm));
            idf.put(term, value);
        }
    }

    public double[] transform(String doc) {
        double[] vec = new double[vocabIndex.size()];
        String[] tokens = TOKEN_SPLIT.split(doc.toLowerCase());
        Map<String, Integer> tf = new HashMap<>();
        for (String t : tokens) {
            if (t.isBlank()) continue;
            tf.put(t, tf.getOrDefault(t, 0) + 1);
        }
        for (Map.Entry<String, Integer> e : tf.entrySet()) {
            String term = e.getKey();
            Integer idx = vocabIndex.get(term);
            if (idx == null) continue;
            double tfValue = e.getValue();
            double idfValue = idf.getOrDefault(term, 0.0);
            vec[idx] = tfValue * idfValue;
        }
        return vec;
    }

    public double[] transformFromDocIndex(int index) {
        double[] vec = new double[vocabIndex.size()];
        if (index < 0 || index >= termFrequencies.size()) return vec;
        Map<String, Integer> tf = termFrequencies.get(index);
        for (Map.Entry<String, Integer> e : tf.entrySet()) {
            String term = e.getKey();
            Integer idx = vocabIndex.get(term);
            if (idx == null) continue;
            double tfValue = e.getValue();
            double idfValue = idf.getOrDefault(term, 0.0);
            vec[idx] = tfValue * idfValue;
        }
        return vec;
    }
}
