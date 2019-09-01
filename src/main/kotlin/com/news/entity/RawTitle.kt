package com.news.entity

import javax.persistence.Id;
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class RawTitle(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,

        @Column(nullable = false)
        val newsDate: Date,

        @Column(nullable = false)
        var tag: String,

        @Column(columnDefinition="text")
        var context: String
)