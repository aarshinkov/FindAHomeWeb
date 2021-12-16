package com.plamena.findahomeweb.responses.estates;

import com.plamena.findahomeweb.utils.PageImpl;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstatesCollection implements Serializable {

  private PageImpl page;
  private List<EstateGetResponse> data;
}
