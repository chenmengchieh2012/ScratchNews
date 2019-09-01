package com.news.entity

import javax.persistence.Id;
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class CutTitle(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,

        @Column(nullable = false)
        var analysisDate: Date,

        @Column(nullable = false)
        var newsDate: Date,

        @Column(nullable = false)
        var tag: String,

        @Column(columnDefinition="text")
        var context: String
)