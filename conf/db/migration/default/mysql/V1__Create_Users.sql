CREATE TABLE `users`(
  `id`    BIGINT  AUTO_INCREMENT,
  `name`  VARCHAR (255)  NOT NULL ,
  `email` VARCHAR (255)   NOT NULL ,
  `password` VARCHAR (255) NOT NULL ,
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
);