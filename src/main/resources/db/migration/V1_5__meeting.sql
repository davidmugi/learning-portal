
DROP TABLE IF EXISTS `meetings`;
CREATE TABLE `meetings`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NULL DEFAULT NULL ,
    `start_time` DATE NULL DEFAULT NULL ,
     `grade_id` INT (11) NULL DEFAULT NULL ,
    `flag` VARCHAR (30) NULL DEFAULT NULL ,
    `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    `created_by` INT(11) NULL DEFAULT NULL ,
    `last_modified_by` INT(11) NULL DEFAULT NULL ,

    PRIMARY KEY (`id`),
    KEY `grade_id` (`grade_id`),
    KEY `created_by` (`created_by`),
    CONSTRAINT `meeting_fk_1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
    CONSTRAINT `meeting_fk_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
)

    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;