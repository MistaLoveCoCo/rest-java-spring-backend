package com.example.restapimoviedb.controllers;

import com.example.restapimoviedb.models.Customized;
import com.example.restapimoviedb.models.Movie;
import com.example.restapimoviedb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity getAllMovies() {

        Customized c = new Customized("List of Movies", movieService.getMovies());
        return new ResponseEntity(c, HttpStatus.OK);

    }

    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity insertMovies(@RequestBody Movie movie) {
        movieService.insertIntoMovie(movie);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getMovieById(@PathVariable("id") String id) {
        Customized c = null;
        try {
            c = new Customized("Found the Movie", Collections.singletonList(movieService.getMoviebyId(id)));
        } catch (Exception e) {
            c = new Customized(e.getMessage(), null);
            return new ResponseEntity(c, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity(c, HttpStatus.OK);
    }

    @PutMapping(value = "/movies/put/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateMovie(@RequestBody Movie movie, @PathVariable("id") String id) {
        Customized c = new Customized("We have Update the data of movie " + movie.getTitle(), Collections.singletonList(movieService.updateMovieContent(movie, id)));
        movieService.updateMovieContent(movie, id);

        return new ResponseEntity(c, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteMoviebyid(@PathVariable("id") String id) {

        movieService.deleteMovie(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/movies/find")
    public ResponseEntity searchMovieByTitle(@RequestParam("name") String title)
    {
        Customized c = new Customized("List of Movies Matching String "+title, movieService.searchByTitle(title));
        return new ResponseEntity(c,HttpStatus.OK);
    }
    @GetMapping(value = "/movies/find")
    public ResponseEntity searchMovieByFeatured(@RequestParam("featured") String featured)
    {
        Customized c = new Customized("List of Movies Matching String "+featured, movieService.searchByFeatured(featured));
        return new ResponseEntity(c,HttpStatus.OK);
    }
}
