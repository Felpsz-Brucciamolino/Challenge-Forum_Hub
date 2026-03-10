CREATE TABLE topicos(

    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    data_criacao DATETIME NOT NULL,

    usuario_id BIGINT,
    curso_id BIGINT,

    PRIMARY KEY(id),

    CONSTRAINT fk_topico_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id),

    CONSTRAINT fk_topico_curso
        FOREIGN KEY (curso_id)
            REFERENCES cursos(id)
);