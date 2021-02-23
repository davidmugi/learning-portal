
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR (100) NULL DEFAULT NULL ,
    `grade_id` INT(11) NULL DEFAULT NULL ,
    `description` VARCHAR (500) NULL DEFAULT NULL ,
    `content_link` VARCHAR (30) NULL DEFAULT NULL ,
    `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    `created_by` INT(11) NULL DEFAULT NULL ,
    `flag` VARCHAR (30) NULL DEFAULT NULL ,

     PRIMARY KEY (`id`),
     KEY  `grade_id` (`grade_id`),
     CONSTRAINT `content_fk_1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;