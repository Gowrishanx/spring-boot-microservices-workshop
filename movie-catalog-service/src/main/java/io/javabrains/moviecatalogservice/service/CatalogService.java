package io.javabrains.moviecatalogservice.service;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CatalogService {

  private RestTemplate restTemplate;

  @Value("${downstream.service.url.ratings-data-service}")
  private final String RATINGS_DATA_SERVICE = null;

  @Value("${downstream.service.url.movie-info-service}")
  private final String MOVIE_INFO_SERVICE = null;

  public List<CatalogItem> getCatalog(String userId) {

    long preTimeMillis = System.currentTimeMillis();

    UserRating userRating = restTemplate.getForObject(RATINGS_DATA_SERVICE + userId, UserRating.class);

    long postTimeMillis = System.currentTimeMillis();

    log.info("time lapsed in getting response from ratings data service is =" + (postTimeMillis - preTimeMillis));

    return userRating.getRatings().stream()
            .map(rating -> {
              Movie movie = restTemplate.getForObject(MOVIE_INFO_SERVICE + rating.getMovieId(), Movie.class);
              return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
            })
            .collect(Collectors.toList());


  }
}
