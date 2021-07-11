package de.impro.api

import de.impro.dto.SuggestionGroupedDto
import de.impro.dto.SuggestionSubmit
import de.impro.model.Suggestion
import de.impro.repository.SuggestionRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject

@Validated
@Controller("/api")
open class SuggestionController {
    @Inject
    lateinit var suggestionRepository: SuggestionRepository

    @Get(uri = "/sugesstions", produces = [MediaType.APPLICATION_JSON])
    fun getAllSuggestions(): List<SuggestionGroupedDto> {
        return suggestionRepository.findAllSuggestionsGrouped().toList() //
            .map { it -> it.copy(it.text.uppercase()) } //
            .groupBy { it.text } //
            .map { (k, v) -> SuggestionGroupedDto(k, v.sumOf { it.value }) }
    }

    @Post("/submitsuggestion", consumes = [MediaType.APPLICATION_JSON])
    fun addSuggestion(suggestions: List<SuggestionSubmit>) {
        suggestionRepository.saveAll(suggestions.map { it -> it.toSuggestion() })
    }
}

fun SuggestionSubmit.toSuggestion() = Suggestion(word = word, id = null)