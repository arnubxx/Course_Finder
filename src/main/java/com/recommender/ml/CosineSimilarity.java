package com.recommender.ml;

public class CosineSimilarity {

    
    public static double compute(double[] a, double[] b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        int n = Math.min(a.length, b.length);
        
        for (int i = 0; i < n; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        
        double denom = Math.sqrt(normA) * Math.sqrt(normB);
        if (denom == 0.0) return 0.0;
        
        return dot / denom;
    }
}
