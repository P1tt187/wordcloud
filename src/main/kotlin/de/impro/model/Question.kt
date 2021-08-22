package de.impro.model

import java.time.LocalDateTime
import javax.persistence.*
import org.hibernate.annotations.CreationTimestamp

@Entity
data class Question(
    @Id @GeneratedValue var id: Long?,
    var word: String,
    @Column(name = "input_time") @CreationTimestamp var inputTime: LocalDateTime?,
    @OneToMany(mappedBy = "question") var suggestions: Set<Suggestion>?
) {
  constructor(
      word: String
  ) : this(inputTime = null, id = null, word = word, suggestions = emptySet())
}
