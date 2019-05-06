package io.javabrains.moviecatalogservice.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
@AllArgsConstructor
public class CatalogRepository {

    @Value("${downstream.service.url.gateway}")
    private final String GATEWAY = null;
    @Value("${downstream.service.url.ratingsDataService}")
    private final String RATINGS_DATA_SERVICE = null;
    @Value("${downstream.service.url.movieInfoService}")
    private final String MOVIE_INFO_SERVICE = null;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand()
    public UserRating getRatings(String userId) {
        long preTimeMillis = System.currentTimeMillis();

        UserRating userRating = restTemplate.getForObject(GATEWAY + RATINGS_DATA_SERVICE + userId, UserRating.class);

        long postTimeMillis = System.currentTimeMillis();

        log.info("time lapsed in getting response from ratings data service is =" + (postTimeMillis - preTimeMillis));

        return userRating;
    }

    @HystrixCommand
    public Movie getMovieInfo(Rating rating) {
        long preTimeMillis = System.currentTimeMillis();

        Movie movie = restTemplate.getForObject(GATEWAY + MOVIE_INFO_SERVICE + rating.getMovieId(), Movie.class);

        long postTimeMillis = System.currentTimeMillis();

        log.info("time lapsed in getting response from movie info service is =" + (postTimeMillis - preTimeMillis));

        return movie;
    }
}
