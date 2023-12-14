CREATE SCHEMA IF NOT EXISTS `Diagnomind` DEFAULT CHARACTER SET utf8;
USE `Diagnomind`;

# hospital taula
create table if not exists `Diagnomind`.`hospital` (
  `gid`               bigint auto_increment,
  `name`              varchar(255),
  `subscription_plan` int,
  `subscriptionStart` date,
  `subscriptionEnd`   date,
  primary key (`gid`))
engine = InnoDB;

create table if not exists `Diagnomind`.`user` (
  `uid`           bigint auto_increment,
  `name`          varchar(255),
  `surname`       varchar(255),
  `ssn`           varchar(255),
  `hospital_gid`  bigint,
  primary key (`uid`), 
  foreign key (`hospital_gid`) references `hospital` (`gid`))
engine = InnoDB;

create table if not exists `Diagnomind`.`image` (
  `id`          bigint,
  `imageData`   mediumblob not null, -- blob 32Mb
  `request_id`  bigint,
  primary key(`id`),
  foreign key (`request_id`) references `request` (`id`)) 
engine = InnoDB;

create table if not exists `Diagnomind`.`feedback` (
  `id`                  bigint,
  `isCorrectDiagnosis`  bool,
  `request_id`          bigint,
  primary key(`id`),
  foreign key (`request_id`) references `request` (`id`))
engine = InnoDB;

create table if not exists `Diagnomind`.`request` (
  `id`          bigint auto_increment,
  `date`        date,
  `image_id`    varchar(255),
  `feedback_id` bigint,
  `user_uid`    bigint,
  primary key (`id`),
  foreign key (`user_uid`) references `user` (`uid`),
  foreign key (`feedback_id`) references `feedback` (`id`),
  foreign key (`image_id`) references `image` (`id`))
engine = InnoDB;

create table if not exists `Diagnomind`.`TrainingData` (
  `id`  bigint,
  primary key(`id`))
engine = InnoDB;

create table if not exists `Diagnomind`.`TrainingImage` (
  `id`                  bigint,
  `isCorrectDiagnosis`  bool,
  `image_id`            bigint,
  `trainingdata_id`     bigint,
  primary key (`id`),
  foreign key (`trainingdata_id`) references `trainingdata` (`id`))
engine = InnoDB;
