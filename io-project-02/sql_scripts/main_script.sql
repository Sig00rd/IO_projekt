-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema io-project-02
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema io-project-02
-- -----------------------------------------------------
DROP SCHEMA  IF EXISTS `io-project-02`;
CREATE SCHEMA IF NOT EXISTS `io-project-02` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `io-project-02` ;

-- -----------------------------------------------------
-- Table `io-project-02`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

CREATE TABLE IF NOT EXISTS `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `role`(name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');


-- -----------------------------------------------------
-- Table `io-project-02`.`sport_object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sport_object` ;

CREATE TABLE IF NOT EXISTS `sport_object` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL ,
  `address` VARCHAR(45) NOT NULL ,
  `city` VARCHAR(45) NOT NULL ,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `sport_object`(`id`,`name`,`address`,`city`,`type`)
VALUES 
('1','Sadzawki','Sadzawki 1','Kraków','ORLIK'),
('2','Szkolna','Szkolna 2','Kraków','HALA');
-- -----------------------------------------------------
-- Table `io-project-02`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` CHAR(80) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `user` (`id`, `username`, `password`, `email`) 
VALUES ('1', 'julo', '$2a$10$M5hNE7kgr0Waj5bzJfJfV.JwsgAlfTCeyS91hoNiv1Zoq.uyGXQ6u', 'julo@gmail.com'),
('2', 'bodzio', '$2a$10$M5hNE7kgr0Waj5bzJfJfV.JwsgAlfTCeyS91hoNiv1Zoq.uyGXQ6u', 'bodzio@gmail.com'),
('3', 'kuba', '$2a$10$M5hNE7kgr0Waj5bzJfJfV.JwsgAlfTCeyS91hoNiv1Zoq.uyGXQ6u', 'kuba@gmail.com');
-- -----------------------------------------------------
-- Table `io-project-02`.`users_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users_roles` ;

CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FK_ROLE_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FK_ROLE`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`),
  CONSTRAINT `FK_USER_05`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `users_roles`(`user_id`,`role_id`)
VALUES 
('1','1'),
('2','1'),
('3','1'),
('1','2');

-- -----------------------------------------------------
-- Table `discipline`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `discipline` ;

CREATE TABLE IF NOT EXISTS `discipline` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `discipline`(name)
VALUES 
('FOOTBALL'),('VOLLEYBALL');
-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cost` FLOAT NOT NULL,
  `needed` INT NOT NULL,
  `priority_needed` INT DEFAULT NULL,
  `enrolled` INT NOT NULL DEFAULT 0,
  `priority_enrolled` INT DEFAULT NULL,
  `date` DATETIME NOT NULL,
  `level` SMALLINT NOT NULL,
  `priority_date` DATETIME DEFAULT NULL,
  `sport_object_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `discipline_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_game_sport_object1_idx` (`sport_object_id` ASC) VISIBLE,
  INDEX `fk_game_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_game_discipline1_idx` (`discipline_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_sport_object1`
    FOREIGN KEY (`sport_object_id`)
    REFERENCES `sport_object` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_discipline1`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `discipline` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION )
ENGINE = InnoDB;

INSERT INTO `game` (`id`, `cost`, `needed`, `date`, `level`,`sport_object_id`, `user_id`, `discipline_id`) 
VALUES 
('1', '10', '5', '2018-01-06 11:00:00','2', '2', '3', '2'),
('2', '0', '3', '2018-01-06 13:00:00','3', '1', '1', '1');

-- -----------------------------------------------------
-- Table `user_games`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `user_games` ;

CREATE TABLE IF NOT EXISTS `user_games` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  `pitch_role_id` INT DEFAULT NULL,
  `created` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_has_game_game1_idx` (`game_id` ASC) VISIBLE,
  INDEX `fk_user_has_game_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_has_game_pitch_role1_idx` (`pitch_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_game_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_game_pitch_role1`
	FOREIGN KEY (`pitch_role_id`)
    REFERENCES `pitch_role`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `pitch_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitch_role` ;

CREATE TABLE IF NOT EXISTS `pitch_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `discipline_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pitch_role_discipline1_idx` (`discipline_id` ASC) VISIBLE,
  CONSTRAINT `fk_pitch_role_discipline1`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `discipline` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `pitch_role` (`id`, `name`, `discipline_id`) 
VALUES 
('1', 'GOALKEEPER', '1'),
('2', 'DEFENDER', '1'),
('3', 'STRIKER', '1'),
('4', 'LIBERO', '2');

-- -----------------------------------------------------
-- Table `games_priorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_priorities` ;

CREATE TABLE IF NOT EXISTS `game_priorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `needed` INT NOT NULL,
  `game_id` INT(11) DEFAULT NULL,
  `pitch_role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_game_priorities_game1_idx` (`game_id` ASC) VISIBLE,
  INDEX `fk_game_priorities_pitch_role1_idx` (`pitch_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_priorities_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_priorities_pitch_role1`
    FOREIGN KEY (`pitch_role_id`)
    REFERENCES `pitch_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
