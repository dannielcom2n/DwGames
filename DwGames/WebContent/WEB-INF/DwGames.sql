

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telefone` int(8) DEFAULT NULL,
  `sexo` varchar(25) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente_user_idx` (`userid`),
  CONSTRAINT `fk_cliente_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) 

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigoDeBarras` int(25) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `valor` double(7,1) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `quantidade` int(10) DEFAULT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `foto` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) 

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
)


CREATE TABLE `compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(8) DEFAULT NULL,
   `clienteid` int(11) NOT NULL,
  `produtoid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_compra_produto_idx` (`produtoid`),
  CONSTRAINT `fk_compra_produto` FOREIGN KEY (`produtoid`) REFERENCES `produto` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
   KEY `fk_compra_cliente_idx` (`clienteid`),
   CONSTRAINT `fk_compra_cliente` FOREIGN KEY (`clienteid`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) 