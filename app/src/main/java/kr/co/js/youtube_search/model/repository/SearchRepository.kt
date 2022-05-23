package kr.co.js.youtube_search.model.repository

import kr.co.js.youtube_search.model.dao.SearchDao

class SearchRepository(
    private val searchDao: SearchDao
) {

    val allSearchResult = searchDao.getAllSearchResult()
}