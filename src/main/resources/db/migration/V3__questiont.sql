DROP TABLE IF EXISTS "question";


CREATE TABLE "question"
(
    id   BIGINT NOT NULL serial,
    word VARCHAR(255),
    input_time  datetime not null ,
    CONSTRAINT pk_question PRIMARY KEY (id)
);


Alter Table "suggestion"  ADD COLUMN question_id BIGINT;
Alter Table "suggestion"  ADD CONSTRAINT "fk_question" FOREIGN KEY (question_id) REFERENCES question(id) ;