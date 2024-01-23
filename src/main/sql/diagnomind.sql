DROP DATABASE IF EXISTS `diagnomind`;
CREATE DATABASE IF NOT EXISTS `diagnomind` DEFAULT CHARACTER SET utf8;
USE `diagnomind`;

CREATE TABLE IF NOT EXISTS `hospital` (
  `id`                  BIGINT AUTO_INCREMENT,
  `name`                VARCHAR(255) NOT NULL,
  `subscription_plan`   TINYINT NOT NULL CHECK (`subscription_plan` BETWEEN 0 AND 2),
  `subscription_start`  DATE NOT NULL,
  `subscription_end`    DATE NOT NULL,
  CONSTRAINT `HOSPITAL_PK` PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `user` (
  `id`          BIGINT AUTO_INCREMENT,
  `name`        VARCHAR(255) NOT NULL,
  `surname`     VARCHAR(255) NOT NULL,
  `ssn`         VARCHAR(255) NOT NULL,
  `hospital_id` BIGINT NOT NULL,
  CONSTRAINT `USER_PK` PRIMARY KEY (`id`), 
  CONSTRAINT `USER_HOSPITAL_FK` FOREIGN KEY (`hospital_id`) REFERENCES `hospital`(`id`)
) ENGINE = InnoDB;