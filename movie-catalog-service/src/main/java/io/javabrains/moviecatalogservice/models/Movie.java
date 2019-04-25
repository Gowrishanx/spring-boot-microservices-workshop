package io.javabrains.moviecatalogservice.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Movie {
  private String movieId;
  private String name;
  private String description;

}
