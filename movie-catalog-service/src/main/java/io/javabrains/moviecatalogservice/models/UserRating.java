package io.javabrains.moviecatalogservice.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRating {

  private String userId;
  private List<Rating> ratings;

}
