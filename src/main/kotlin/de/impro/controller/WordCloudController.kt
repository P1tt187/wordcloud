package de.impro.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View
import io.swagger.v3.oas.annotations.Hidden
import java.nio.charset.StandardCharsets
import javax.inject.Singleton

@Singleton
@Controller("/views")
@Hidden
class WordCloudController {
    @View("cloud")
    @Get("/cloud")
    fun cloud() : HttpResponse<Void> {
        return HttpResponse.ok<Void?>().characterEncoding(StandardCharsets.UTF_8);
    }
}