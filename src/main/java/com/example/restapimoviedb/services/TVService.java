package com.example.restapimoviedb.services;

import com.example.restapimoviedb.models.TVSeries;
import com.example.restapimoviedb.models.TVSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVService {

    @Autowired
    private TVSeriesRepository tvSeriesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TVSeries> getTVSeries() {

        return tvSeriesRepository.findAll();
    }

    public void insertTVSeries(TVSeries tvSeries) {

        tvSeriesRepository.insert(tvSeries);
    }

    public List<TVSeries> searchByTitle(String title) {

        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("title").regex(title));
        List<TVSeries> tvFound = mongoTemplate.find(searchQuery, TVSeries.class);
        return tvFound;
    }

    public Optional<TVSeries> getTVByID(String ID) throws Exception {

        Optional<TVSeries> searchTVSeriesID = tvSeriesRepository.findById(ID);

        if (!searchTVSeriesID.isPresent()) {

            throw new Exception("TV with ID" + ID + " is not present.");
        }
        return searchTVSeriesID;
    }

    public TVSeries updateTVContent(TVSeries tvSeries, String ID) {

        Optional<TVSeries> searchID = tvSeriesRepository.findById(ID);

//        searchID.get().setCategory(tvSeries.getCategory());
        searchID.get().setDescription(tvSeries.getDescription());
//        searchID.get().setFeatured(tvSeries.getFeatured());
//        searchID.get().setFiletitle(tvSeries.getFiletitle());
        searchID.get().setRating(tvSeries.getRating());
        searchID.get().setTitle(tvSeries.getTitle());
//        searchID.get().setPoster(tvSeries.getPoster());
//        searchID.get().setBuyprice(tvSeries.getBuyprice());
//        searchID.get().setRentprice(tvSeries.getRentprice());

        TVSeries afterUpdate = tvSeriesRepository.save(searchID.get());
        return afterUpdate;
    }

    public void deleteTVSeries(String ID) {

        tvSeriesRepository.deleteById(ID);
    }
}
