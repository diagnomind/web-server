DROP DATABASE IF EXISTS 'diagnomind';
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

CREATE TABLE IF NOT EXISTS `training_image` (
  `id`                    BIGINT AUTO_INCREMENT,
  `is_correct_diagnosis`  BOOL NOT NULL,
  CONSTRAINT `TRAINING_IMAGE_PK` PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `image` (
  `id`                BIGINT AUTO_INCREMENT,
  `image_data`        MEDIUMBLOB NOT NULL, -- blob 32Mb
  `training_image_id` BIGINT NOT NULL,
  CONSTRAINT `IMAGE_PK` PRIMARY KEY(`id`),
  CONSTRAINT `IMAGE_TRAINING_IMAGE` FOREIGN KEY (`training_image_id`) REFERENCES `training_image`(`id`)
) ENGINE = InnoDB;