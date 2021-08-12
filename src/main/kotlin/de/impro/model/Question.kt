package de.impro.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Question(
    @Id
    @GeneratedValue
    var id : Long? ,
    
    var word: String, 
  
    @Column(name="input_time")
    @CreationTimestamp
    var inputTime: LocalDateTime?,

    @OneToMany(mappedBy="question")        
    var suggestions:Set<Suggestion>?
){
    constructor(word:String):
        this(inputTime=null,id=null,word=word, suggestions=emptySet())
    
}