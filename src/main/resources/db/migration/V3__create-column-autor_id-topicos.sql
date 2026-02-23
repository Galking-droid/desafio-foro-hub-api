-- V3__create-column-autor_id-topicos.sql
ALTER TABLE topicos ADD autor_id BIGINT NOT NULL;

ALTER TABLE topicos ADD CONSTRAINT fk_topicos_autor_id
    FOREIGN KEY (autor_id) REFERENCES usuarios(id);