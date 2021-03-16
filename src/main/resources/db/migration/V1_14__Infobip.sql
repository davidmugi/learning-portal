set foreign_key_checks = 0;

CREATE TABLE `infobip_config`(

                                 `id` INT(11) NOT NULL AUTO_INCREMENT,
                                 `name` VARCHAR (45) NULL DEFAULT NULL ,
                                 `sender_name` VARCHAR (45) NULL DEFAULT NULL ,
                                 `username` VARCHAR (60) NULL DEFAULT NULL ,
                                 `password` VARCHAR (60) NULL DEFAULT NULL ,
                                 `base_url` VARCHAR (60) NULL DEFAULT NULL ,
                                 `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                                 `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                                 `created_by` INT(11) NULL DEFAULT NULL ,
                                 `last_modified_by` INT(11) NULL DEFAULT NULL ,
                                 `flag` VARCHAR (45) NULL DEFAULT NULL ,


                                 PRIMARY KEY (`id`)

)
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB;

INSERT INTO  `infobip_config` (id, name, sender_name, username, password, base_url,flag)
VALUES (1,'Portal','','','','https://api.infobip.com','1');

set foreign_key_checks = 1;