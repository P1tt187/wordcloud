package de.impro.repository

import de.impro.dto.SuggestionGroupedDto
import de.impro.model.Question
import de.impro.model.Suggestion
import io.micronaut.context.annotation.Parameter
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.query.builder.sql.SqlQueryConfiguration
import io.micronaut.data.repository.CrudRepository
import java.math.BigInteger

@Repository
interface SuggestionRepository : CrudRepository<Suggestion, BigInteger> {

    @Query(" select new de.impro.dto.SuggestionGroupedDto(s.word, count(s.word)) from Suggestion as s where s.question = :question group by s.word")
    fun findAllSuggestionsGrouped(question: Question):MutableList<SuggestionGroupedDto>

}