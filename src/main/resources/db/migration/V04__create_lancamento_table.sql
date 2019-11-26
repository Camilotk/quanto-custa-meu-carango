CREATE TABLE lancamento (
   	id IDENTITY PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	id_categoria BIGINT(20) NOT NULL,
	id_carro BIGINT(20) NOT NULL,
	FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (id_carro) REFERENCES carro(id) ON DELETE CASCADE ON UPDATE NO ACTION
);
