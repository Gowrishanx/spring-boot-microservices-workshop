package io.javabrains.moviecatalogservice.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Rating {

  private String movieId;
  private int rating;

}
