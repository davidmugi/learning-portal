
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR (11) NULL DEFAULT NULL ,
    `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    `last_modified_by` INT(11) NULL DEFAULT NULL ,
    `created_by` INT(11) NULL DEFAULT NULL ,
    `flag` VARCHAR (30) NULL DEFAULT NULL ,

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;