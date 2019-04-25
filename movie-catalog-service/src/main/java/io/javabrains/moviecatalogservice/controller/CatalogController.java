package io.javabrains.moviecatalogservice.controller;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@Slf4j
@AllArgsConstructor
public class CatalogController {

  @Autowired
  WebClient.Builder webClientBuilder;

  private CatalogService catalogService;

  @RequestMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

    log.info("logging in movie catalogue controller for userId " + userId);

    return  catalogService.getCatalog(userId);

  }
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/