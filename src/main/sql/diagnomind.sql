CREATE SCHEMA IF NOT EXISTS `Diagnomind` DEFAULT CHARACTER SET utf8;
USE `Diagnomind`;

# hospital taula
create table if not exists `hospital` (
  `gid`                 bigint auto_increment,
  `name`                varchar(255),
  `subscription_plan`   int,
  `subscription_start`  date,
  `subscription_end`    date,
  primary key (`gid`))
engine = InnoDB;

create table if not exists `user` (
  `uid`           bigint auto_increment,
  `name`          varchar(255),
  `surname`       varchar(255),
  `ssn`           varchar(255),
  `hospital_gid`  bigint,
  primary key (`uid`))
engine = InnoDB;

create table if not exists `image` (
  `id`          bigint,
  `image_data`  mediumblob not null, -- blob pilla 32Mb
  `request_id`  bigint,
  primary key(`id`)) 
engine = InnoDB;

create table if not exists `feedback` (
  `id`                    bigint,
  `is_correct_diagnosis`  bool,
  `request_id`            bigint,
  primary key(`id`))
engine = InnoDB;

create table if not exists `request` (
  `id`          bigint auto_increment,
  `date`        date,
  `image_id`    varchar(255),
  `feedback_id` bigint,
  `user_uid`    bigint,
  primary key (`id`))
engine = InnoDB;

create table if not exists `training_data` (
  `id`  bigint,
  primary key(`id`))
engine = InnoDB;

create table if not exists `training_image` (
  `id`                    bigint,
  `is_correct_diagnosis`  bool,
  `image_id`              bigint,
  `trainingdata_id`       bigint,
  primary key(`id`))
engine = InnoDB;
