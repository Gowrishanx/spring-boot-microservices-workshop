package io.javabrains.moviecatalogservice.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Rating {

  private String movieId;
  private int rating;

}
