package com.plamena.findahomeweb.responses.users;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleGetResponse implements Serializable {

  private String role;
}
