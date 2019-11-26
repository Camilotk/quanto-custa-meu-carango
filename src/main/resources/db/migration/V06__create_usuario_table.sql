CREATE TABLE usuario (
	id IDENTITY PRIMARY KEY,
	login VARCHAR(500) NOT NULL,
	senha VARCHAR(500)
);

INSERT INTO usuario(login, senha) VALUES('ADMIN', '$2a$10$1DvQ54B5eFQKheeBvuqBzu5uw.pvPqQjCww1u3SA3BiVIrDALN./C');
