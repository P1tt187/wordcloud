package de.impro.repository

import de.impro.dto.SuggestionGroupedDto
import de.impro.model.Question
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.query.builder.sql.SqlQueryConfiguration
import io.micronaut.data.repository.CrudRepository
import java.math.BigInteger

@Repository
interface QuestionRepository : CrudRepository<Question, BigInteger>{

}