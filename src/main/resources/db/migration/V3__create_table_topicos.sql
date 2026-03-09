CREATE TABLE topicos(

                        id BIGINT NOT NULL AUTO_INCREMENT,
                        titulo VARCHAR(255) NOT NULL,
                        mensagem TEXT NOT NULL,

                        autor_id BIGINT,
                        curso_id BIGINT,

                        PRIMARY KEY(id),

                        CONSTRAINT fk_topico_autor
                            FOREIGN KEY (autor_id)
                                REFERENCES autores(id),

                        CONSTRAINT fk_topico_curso
                            FOREIGN KEY (curso_id)
                                REFERENCES cursos(id)

);