package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsResource.class);

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {

        LOGGER.trace("logging in ratings data service - getting movie rating for " + movieId);
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        LOGGER.trace("logging in ratings data service - getting user rating for " + userId);
        return userRating;

    }

}
