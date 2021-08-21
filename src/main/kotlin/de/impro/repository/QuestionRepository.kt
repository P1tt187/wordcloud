package de.impro.repository

import de.impro.dto.SuggestionGroupedDto
import de.impro.model.Question
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.QueryHint
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.query.builder.sql.SqlQueryConfiguration
import io.micronaut.data.repository.CrudRepository
import org.hibernate.jpa.QueryHints
import java.math.BigInteger

@Repository
interface QuestionRepository : CrudRepository<Question, BigInteger>{

    @Query("select q from Question q  order by q.inputTime desc ")
    @io.micronaut.data.annotation.repeatable.QueryHints( value = [QueryHint(name = QueryHints.HINT_FETCH_SIZE, value =  "1"), QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")])
    fun findLatestQuestion():Question?

}