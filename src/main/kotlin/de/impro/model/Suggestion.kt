package de.impro.model

import io.micronaut.data.annotation.MappedEntity
import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import java.math.BigInteger
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Suggestion(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suggestion_generator")
    @SequenceGenerator(name = "suggestion_generator" ,sequenceName = "suggestion_seq", allocationSize = 1)
    var id:Long?, var word:String, 
    
    @Column(name="input_time")
    @CreationTimestamp
    var inputTime: LocalDateTime?,
    
    @ManyToOne()
    @JoinColumn(name="question_id")
    var question:Question
    ) {

    constructor(  word: String,  question: Question ) :
        this(id=null, word=word, question=question, inputTime=null)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Suggestion

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 535691891

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(word = $word )"
    }
}

