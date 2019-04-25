package io.javabrains.moviecatalogservice.repository;

import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
@AllArgsConstructor
public class CatalogRepository {

  private RestTemplate restTemplate;

  @Value("${downstream.service.url.ratings-data-service}")
  private final String RATINGS_DATA_SERVICE = null;
  @Value("${downstream.service.url.movie-info-service}")
  private final String MOVIE_INFO_SERVICE = null;

  public UserRating getRatings(String userId) {
    long preTimeMillis = System.currentTimeMillis();

    UserRating userRating = restTemplate.getForObject(RATINGS_DATA_SERVICE + userId, UserRating.class);

    long postTimeMillis = System.currentTimeMillis();

    log.info("time lapsed in getting response from ratings data service is =" + (postTimeMillis - preTimeMillis));

    return userRating;
  }

  public Movie getMovieInfo(Rating rating) {
    long preTimeMillis = System.currentTimeMillis();

    Movie movie = restTemplate.getForObject(MOVIE_INFO_SERVICE + rating.getMovieId(), Movie.class);

    long postTimeMillis = System.currentTimeMillis();

    log.info("time lapsed in getting response from movie info service is =" + (postTimeMillis - preTimeMillis));

    return movie;
  }
}
