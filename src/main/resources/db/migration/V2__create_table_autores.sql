CREATE TABLE autores(

                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nome VARCHAR(100) NOT NULL,
                        email VARCHAR(150) NOT NULL,

                        PRIMARY KEY(id),
                        UNIQUE(nome),
                        UNIQUE(email)

);