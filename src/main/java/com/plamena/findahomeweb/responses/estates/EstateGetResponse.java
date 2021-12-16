package com.plamena.findahomeweb.responses.estates;

import com.plamena.findahomeweb.responses.users.UserGetResponse;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstateGetResponse implements Serializable {

  private String estateId;
  private String name;
  private String country;
  private String city;
  private String neighborhood;
  private Double price;
  private Integer rooms;
  private Integer floor;
  private Double area;
  private String address;
  private Boolean isRent;
  private Timestamp createdOn;
  private Timestamp editedOn;
  private UserGetResponse user;
}
