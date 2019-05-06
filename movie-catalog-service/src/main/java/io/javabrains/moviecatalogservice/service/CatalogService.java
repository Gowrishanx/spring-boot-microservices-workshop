package io.javabrains.moviecatalogservice.service;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CatalogService {

  private CatalogRepository catalogRepository;


  public List<CatalogItem> getCatalog(String userId) {

    UserRating userRating = catalogRepository.getRatings(userId);


    return userRating.getRatings().stream()
            .map(rating -> {
              Movie movie = catalogRepository.getMovieInfo(rating);
              return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
            })
            .collect(Collectors.toList());


  }

}
