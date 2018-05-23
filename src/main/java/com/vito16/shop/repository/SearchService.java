package com.vito16.shop.repository;

import javax.naming.directory.SearchResult;

public interface SearchService {
    //搜索
    SearchResult search(String queryString, int page, int row)throws Exception;
}
