package de.impro.api

import de.impro.dto.QuestionSubmit
import de.impro.model.Question
import de.impro.repository.QuestionRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject


@Validated
@Controller
class QuestionController {
    @Inject
    lateinit var qr: QuestionRepository

    @Post("/submitquestion", consumes = [MediaType.APPLICATION_JSON])
    fun addQuestion(quest: QuestionSubmit){
        val question = Question(word = quest.word)
        qr.save(question)

    }
}