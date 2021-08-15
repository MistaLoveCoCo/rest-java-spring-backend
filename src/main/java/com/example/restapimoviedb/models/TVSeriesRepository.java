package com.example.restapimoviedb.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TVSeriesRepository extends MongoRepository<TVSeries, String> {
}
