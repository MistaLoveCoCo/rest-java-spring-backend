package com.example.restapimoviedb.controllers;

import com.example.restapimoviedb.models.Customized;
import com.example.restapimoviedb.models.TVSeries;
import com.example.restapimoviedb.services.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@CrossOrigin("http://localhost:3000")
@Controller
public class TVController {

    @Autowired
    private TVService tvService;

    @GetMapping("/tv")
    public ResponseEntity getAllTV() {
        Customized c = new Customized("List of TV", tvService.getTVSeries());
        return new ResponseEntity(c, HttpStatus.OK);
    }

    @GetMapping("/tv/{id}")
    public ResponseEntity getTVbyId(@PathVariable("id") String id) {
        Customized c = null;
        try {
            c = new Customized("Found the TV", Collections.singletonList(tvService.getTVByID(id)));
        } catch (Exception e) {
            c = new Customized(e.getMessage(), null);
            return new ResponseEntity(c, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity(c, HttpStatus.OK);
    }

    @PostMapping(value = "/tv", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity inserttvbyId(@RequestBody TVSeries tvSeries) {
        tvService.insertTVSeries(tvSeries);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/tv/put/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updatetv(@RequestBody TVSeries tvSeries, @PathVariable("id") String id) {

        Customized c = new Customized("Update data of TV " + tvSeries.getTitle(), Collections.singletonList(tvService.updateTVContent(tvSeries, id)));


        return new ResponseEntity(c, HttpStatus.OK);
    }

    @DeleteMapping("/tv/{id}")
    public ResponseEntity deleteTVbyId(@PathVariable("id") String id) {
        tvService.deleteTVSeries(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/tv/find")
    public ResponseEntity searchtvBytitle(@RequestParam("name") String title)
    {
        Customized c = new Customized("List of Movies Matching String "+title, tvService.searchByTitle(title));
        return new ResponseEntity(c,HttpStatus.OK);
    }
    
    @GetMapping(value = "/tv/find")
    public ResponseEntity searchtvByFeatured(@RequestParam("featured") String featured)
    {
        Customized c = new Customized("List of Movies Matching featured "+featured, tvService.searchByFeatured(featured));
        return new ResponseEntity(c,HttpStatus.OK);
    }
}
