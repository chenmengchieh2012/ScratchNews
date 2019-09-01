package com.news.repository

import com.news.entity.CutTitle
import com.news.entity.RawTitle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RawTitleRepository : JpaRepository<RawTitle, Long> {
    @Query("FROM RawTitle WHERE newsDate >= :startDate AND newsDate <= :endDate")
    fun findAllBydateRange(@Param("startDate") startDate: String, @Param("endDate") endDate: String): List<CutTitle>
}