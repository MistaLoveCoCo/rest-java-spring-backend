package com.example.restapimoviedb.services;

import com.example.restapimoviedb.models.Movie;
import com.example.restapimoviedb.models.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> getMovies() {

        return movieRepository.findAll();
    }

    public void insertIntoMovie(Movie movie) {

        movieRepository.insert(movie);
    }

    public List<Movie> searchByTitle(String title) {

        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("title").regex(title));
        List<Movie> movieFound = mongoTemplate.find(searchQuery, Movie.class);
        return movieFound;
    }
    public List<Movie> searchByFeatured(String featured) {

        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("featured").regex(featured));
        List<Movie> movieFound = mongoTemplate.find(searchQuery, Movie.class);
        return movieFound;
    }
    public Optional<Movie> getMoviebyId(String movieId) throws Exception {

        Optional<Movie> movie = movieRepository.findById(movieId);

        if (!movie.isPresent()) {
            throw new Exception("Movie with id " + movieId + " Not Found");
        }
        return movie;

    }
    public void deleteMovie(String id) {

        movieRepository.deleteById(id);
    }

    public Movie updateMovieContent(Movie movie, String ID) {

        Optional<Movie> searchID = movieRepository.findById(ID);

//        searchID.get().setCategory(movie.getCategory());
        searchID.get().setDescription(movie.getDescription());
//        searchID.get().setFeatured(movie.getFeatured());
//        searchID.get().setFiletitle(movie.getFiletitle());
        searchID.get().setRating(movie.getRating());
        searchID.get().setTitle(movie.getTitle());
//        searchID.get().setPoster(movie.getPoster());
//        searchID.get().setBuyprice(movie.getBuyprice());
//        searchID.get().setRentprice(movie.getRentprice());

        Movie afterUpdate = movieRepository.save(searchID.get());
        return afterUpdate;
    }
}
