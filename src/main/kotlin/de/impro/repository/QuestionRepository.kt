package de.impro.repository

import de.impro.model.Question
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.QueryHint
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.math.BigInteger
import org.hibernate.jpa.QueryHints

@Repository
interface QuestionRepository : CrudRepository<Question, Long> {

  @Query("select q from Question q  order by q.inputTime desc ")
  @io.micronaut.data.annotation.repeatable.QueryHints(
      value =
          [
              QueryHint(name = QueryHints.HINT_FETCH_SIZE, value = "1"),
              QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")])
  fun findLatestQuestion(): Question?
}
