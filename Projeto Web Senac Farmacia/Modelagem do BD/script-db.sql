-- MySQL Workbench Forward Engineering (OUTDATED!!!!)

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema projeto_integrador
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projeto_integrador
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projeto_integrador` DEFAULT CHARACTER SET utf8mb3 ;
USE `projeto_integrador` ;

-- -----------------------------------------------------
-- Table `projeto_integrador`.`departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto_integrador`.`departamentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_departamento` VARCHAR(100) CHARACTER SET 'utf8mb3' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_departamento` (`nome_departamento` ASC) VISIBLE,
  UNIQUE INDEX `nome_departamento_UNIQUE` (`nome_departamento` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `projeto_integrador`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto_integrador`.`categorias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(100) CHARACTER SET 'utf8mb3' NOT NULL,
  `departamentos_id` INT NOT NULL,
  PRIMARY KEY (`id`, `departamentos_id`),
  UNIQUE INDEX `nome_categoria` (`nome_categoria` ASC) VISIBLE,
  INDEX `fk_categoria_departamentos1_idx` (`departamentos_id` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_departamentos1`
    FOREIGN KEY (`departamentos_id`)
    REFERENCES `projeto_integrador`.`departamentos` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `projeto_integrador`.`marcas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto_integrador`.`marcas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_marca` VARCHAR(100) CHARACTER SET 'utf8mb3' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_marca` (`nome_marca` ASC) VISIBLE,
  UNIQUE INDEX `nome_marca_UNIQUE` (`nome_marca` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `projeto_integrador`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto_integrador`.`produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_produto` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `quantidade` INT NOT NULL,
  `marcas_id` INT NOT NULL,
  `categorias_id` INT NOT NULL,
  `categorias_departamentos_id` INT NOT NULL,
  PRIMARY KEY (`id`, `marcas_id`, `categorias_id`, `categorias_departamentos_id`),
  UNIQUE INDEX `nome_produto` (`nome_produto` ASC) VISIBLE,
  INDEX `fk_produtos_marcas1_idx` (`marcas_id` ASC) VISIBLE,
  INDEX `fk_produtos_categorias1_idx` (`categorias_id` ASC, `categorias_departamentos_id` ASC) VISIBLE,
  CONSTRAINT `fk_produtos_categorias1`
    FOREIGN KEY (`categorias_id` , `categorias_departamentos_id`)
    REFERENCES `projeto_integrador`.`categorias` (`id` , `departamentos_id`),
  CONSTRAINT `fk_produtos_marcas1`
    FOREIGN KEY (`marcas_id`)
    REFERENCES `projeto_integrador`.`marcas` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `projeto_integrador`.`vendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto_integrador`.`vendas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_venda` DATE NOT NULL,
  `valor_venda` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
