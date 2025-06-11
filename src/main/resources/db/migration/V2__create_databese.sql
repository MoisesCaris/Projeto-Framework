CREATE TYPE genero_enum AS ENUM ('Masculino', 'Feminino', 'Outros');
CREATE TYPE estado_enum AS ENUM (
    'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA',
    'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN',
    'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
    );

CREATE TABLE Farmacia (
                          idFarmacia SERIAL PRIMARY KEY,
                          nome VARCHAR(150) NOT NULL,
                          cnpj VARCHAR(18) UNIQUE NOT NULL
);

CREATE TABLE Setor (
                       idSetor SERIAL PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       valeRefeicao NUMERIC(10,2) DEFAULT 300.00 CHECK (valeRefeicao >= 0),
                       valeAlimentacao NUMERIC(10,2) DEFAULT 300.00 CHECK (valeAlimentacao >= 0),
                       planoSaude NUMERIC(10,2) DEFAULT 3000.00 CHECK (planoSaude >= 0),
                       planoOdonto NUMERIC(10,2) DEFAULT 3000.00 CHECK (planoOdonto >= 0),
                       valeTransporte NUMERIC(10,2) DEFAULT 0 CHECK (valeTransporte >= 0),
                       idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);

CREATE TABLE Funcionario (
                             idFuncionario SERIAL PRIMARY KEY,
                             nomeCompleto VARCHAR(150) NOT NULL,
                             idade INT NOT NULL CHECK (idade >= 0),
                             genero genero_enum,
                             idSetor INT REFERENCES Setor(idSetor) ON DELETE SET NULL,
                             salarioBase NUMERIC(10,2) NOT NULL CHECK (salarioBase >= 0),
                             idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);

CREATE TABLE Produto (
                         idProduto SERIAL PRIMARY KEY,
                         nomeProduto VARCHAR(150) NOT NULL,
                         valorVenda NUMERIC(10,2) NOT NULL CHECK (valorVenda >= 0),
                         valorCusto NUMERIC(10,2) NOT NULL CHECK (valorCusto >= 0),
                         quantidade INT NOT NULL DEFAULT 0 CHECK (quantidade >= 0),
                         idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);

CREATE TABLE Venda (
                       idVenda SERIAL PRIMARY KEY,
                       idFuncionario INT REFERENCES Funcionario(idFuncionario) ON DELETE SET NULL,
                       dataVenda DATE NOT NULL DEFAULT CURRENT_DATE,
                       totalVenda NUMERIC(10,2) DEFAULT 0.00 CHECK (totalVenda >= 0),
                       idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);

CREATE TABLE VendaProdutos (
                               idVenda INT REFERENCES Venda(idVenda) ON DELETE CASCADE,
                               idProduto INT REFERENCES Produto(idProduto) ON DELETE CASCADE,
                               qtdVendaProduto INT CHECK (qtdVendaProduto > 0),
                               valorVendaProduto NUMERIC(10,2) CHECK (valorVendaProduto >= 0),
                               PRIMARY KEY (idVenda, idProduto)
);

CREATE TABLE Compra (
                        idCompra SERIAL PRIMARY KEY,
                        idFuncionario INT REFERENCES Funcionario(idFuncionario) ON DELETE SET NULL,
                        dataCompra DATE NOT NULL DEFAULT CURRENT_DATE,
                        totalCompra NUMERIC(10,2) DEFAULT 0.00 CHECK (totalCompra >= 0),
                        idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);

CREATE TABLE CompraProdutos (
                                idCompra INT REFERENCES Compra(idCompra) ON DELETE CASCADE,
                                idProduto INT REFERENCES Produto(idProduto) ON DELETE CASCADE,
                                qtdCompraProduto INT CHECK (qtdCompraProduto > 0),
                                valorCompraProduto NUMERIC(10,2) CHECK (valorCompraProduto >= 0),
                                PRIMARY KEY (idCompra, idProduto)
);

CREATE TABLE Transportadora (
                                idTransportadora SERIAL PRIMARY KEY,
                                nome VARCHAR(150) NOT NULL
);


CREATE TABLE CoberturaTransportadora (
                                         idTransportadora INT REFERENCES Transportadora(idTransportadora) ON DELETE CASCADE,
                                         estado estado_enum,
                                         PRIMARY KEY (idTransportadora, estado)
);

CREATE TABLE NegociosEmAndamento (
                                     idNegocio SERIAL PRIMARY KEY,
                                     idCompra INT UNIQUE REFERENCES Compra(idCompra) ON DELETE SET NULL,
                                     idVenda INT UNIQUE REFERENCES Venda(idVenda) ON DELETE SET NULL,
                                     idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);

CREATE TABLE Caixa (
                       idCaixa SERIAL PRIMARY KEY,
                       valor NUMERIC(12,2) NOT NULL DEFAULT 0.00 CHECK (valor >= 0),
                       idFarmacia INT REFERENCES Farmacia(idFarmacia) ON DELETE CASCADE
);