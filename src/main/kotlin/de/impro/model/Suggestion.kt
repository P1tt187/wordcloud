package de.impro.model

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigInteger
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Suggestion(
    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    var id:BigInteger?, var word:String)
