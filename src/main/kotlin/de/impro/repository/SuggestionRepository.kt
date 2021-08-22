package de.impro.repository

import de.impro.dto.SuggestionGroupedDto
import de.impro.model.Question
import de.impro.model.Suggestion
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.QueryHint
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import org.hibernate.jpa.QueryHints
import java.math.BigInteger

@Repository
interface SuggestionRepository : CrudRepository<Suggestion, BigInteger> {

    @Query(" select new de.impro.dto.SuggestionGroupedDto(s.word, count(s.word)) from Suggestion as s where s.question = :question group by s.word")
    @io.micronaut.data.annotation.repeatable.QueryHints( value = [QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")])
    @Cacheable
    fun findAllSuggestionsGrouped(question: Question):MutableList<SuggestionGroupedDto>

}