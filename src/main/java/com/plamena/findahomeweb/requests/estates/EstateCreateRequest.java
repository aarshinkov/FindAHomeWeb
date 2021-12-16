package com.plamena.findahomeweb.requests.estates;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstateCreateRequest implements Serializable {

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String name;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String country;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String city;

  private String neighborhood;

  @NotNull(message = "Полето не може да бъде празно!")
  private Double price;

  @NotNull(message = "Полето не може да бъде празно!")
  private Integer rooms;

  @NotNull(message = "Полето не може да бъде празно!")
  private Integer floor;

  @NotNull(message = "Полето не може да бъде празно!")
  private Double area;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String address;

  @NotNull(message = "Полето не може да бъде празно!")
  private Boolean isRent;
}
