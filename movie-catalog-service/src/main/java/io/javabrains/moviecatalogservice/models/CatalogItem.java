package io.javabrains.moviecatalogservice.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CatalogItem {
  private String name;
  private String desc;
  private int rating;

}
