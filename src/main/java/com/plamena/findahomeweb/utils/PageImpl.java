package com.plamena.findahomeweb.utils;

import lombok.ToString;

@ToString
public class PageImpl extends Page {

  @Override
  public Long getTotalPages() {
    globalTotalElements = getGlobalTotalElements();
    maxElementsPerPage = getMaxElementsPerPage();

    if (globalTotalElements % maxElementsPerPage != 0) {
      return (globalTotalElements / maxElementsPerPage) + 1;
    }

    return globalTotalElements / maxElementsPerPage;
  }

  @Override
  public boolean isFirst() {
    return getCurrentPage() == 1;
  }

  @Override
  public boolean isLast() {
    return (long) getCurrentPage() == getTotalPages();
  }

  @Override
  public boolean hasNext(Integer currentPage) {
    long totalPages = getTotalPages();

    if (totalPages == 1) {
      return false;
    }

    if (totalPages > 1) {
      return currentPage < totalPages;
    }

    return currentPage == totalPages;
  }

  @Override
  public boolean hasPrevious(Integer currentPage) {
    long totalPages = getTotalPages();

    if ((currentPage == totalPages) || (currentPage < totalPages && currentPage > 1)) {
      return true;
    }

    return currentPage != 1;
  }
}
