package de.impro.api

import de.impro.dto.SuggestionGroupedDto
import de.impro.dto.SuggestionSubmit
import de.impro.model.Question
import de.impro.model.Suggestion
import de.impro.repository.QuestionRepository
import de.impro.repository.SuggestionRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject

@Validated
@Controller("/api")
class SuggestionController {
  @Inject lateinit var suggestionRepository: SuggestionRepository

  @Inject lateinit var questionRepository: QuestionRepository

  @Get(uri = "/sugesstions", produces = [MediaType.APPLICATION_JSON])
  fun getAllSuggestions(): List<SuggestionGroupedDto> {
    val question = questionRepository.findLatestQuestion() ?: return emptyList()
    return suggestionRepository
        .findAllSuggestionsGrouped(question)
        .toList() //
        .map { it -> it.copy(it.text.uppercase()) } //
        .groupBy { it.text } //
        .map { (k, v) -> SuggestionGroupedDto(k, v.sumOf { it.value }) }
  }

  @Post("/submitsuggestion", consumes = [MediaType.APPLICATION_JSON])
  fun addSuggestion(suggestions: List<SuggestionSubmit>) {
    val question = questionRepository.findLatestQuestion() ?: return
    suggestionRepository.saveAll(suggestions.map { it -> it.toSuggestion(question) })
  }
}

fun SuggestionSubmit.toSuggestion(question: Question) = Suggestion(word = word, question)
