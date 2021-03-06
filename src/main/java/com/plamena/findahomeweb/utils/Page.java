package com.plamena.findahomeweb.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Page {

  protected Integer currentPage;
  protected Long localTotalElements;
  protected Long globalTotalElements;
  protected Integer maxElementsPerPage = 6;
  protected Integer startPage;
  protected Integer endPage;

  protected abstract Long getTotalPages();

  protected abstract boolean isFirst();

  protected abstract boolean isLast();

  protected abstract boolean hasNext(Integer currentPage);

  protected abstract boolean hasPrevious(Integer currentPage);
}
