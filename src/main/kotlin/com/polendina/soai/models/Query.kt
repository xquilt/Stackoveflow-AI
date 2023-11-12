package com.polendina.soai.models
import java.time.LocalTime

data class Query(
    val query: String,
    val time: LocalTime,
    val id: Int,
    val type: QueryType
)
enum class QueryType{
    QUESTION,
    ANSWER
}