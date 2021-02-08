set foreign_key_checks = 0;

DROP TABLE IF EXISTS `permission_groups`;
CREATE TABLE `permission_groups` (
                                     `id` INT(11) NOT NULL AUTO_INCREMENT,
                                     `name` VARCHAR (45) NULL DEFAULT NULL,
                                     `description` VARCHAR (100) NULL DEFAULT NULL ,
                                     `flag` VARCHAR (30) NULL DEFAULT NULL ,
                                     `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                                     `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

                                     PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO `permission_groups` (name, description, flag)
VALUES ('User Groups', 'ROLE_USER_GROUPS','1'),
       ('Users','ROLE_USER','1'),
       ('Classes','ROLE_CLASSES','1');


DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
                               `id` INT(11) NOT NULL AUTO_INCREMENT,
                               `name` VARCHAR (45) NULL DEFAULT NULL,
                               `app_code` VARCHAR  (45) NULL DEFAULT NULL ,
                               `description` VARCHAR (100) NULL DEFAULT NULL ,
                               `flag` VARCHAR (30) NULL DEFAULT NULL ,
                               `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                               `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                               `permission_group_id` INT (11) NULL DEFAULT NULL ,

                               PRIMARY KEY (`id`),
                               KEY `permission_group_id` (`permission_group_id`),
                               CONSTRAINT `permission_fk_1` FOREIGN KEY (`permission_group_id`) REFERENCES `permission_groups` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO `permissions` (name,app_code,flag,permission_group_id)
VALUES ('View UserGroups','DEFAULT','1',1),
       ('View Active UserGroups', 'view-active','1',1),
       ('View New UserGroups', 'view-new', '1',1),
       ('View Edited UserGroups', 'view-edit', '1',1),
       ('View Deactivated UserGroups', 'view-deactivated', '1', 1),
       ('View Inactive UserGroups', 'view-inactive','1',1),
       ('Create UserGroups', 'new','1', 1),
       ('Edit UserGroups', 'edit','1', 1),
       ('Approve New  UserGroups', 'approve-new', '1', 1),
       ('Decline New UserGroups', 'decline-new', '1', 1),
       ('Approve Edited UserGroups', 'approve-edit', '1', 1),
       ('Decline Edited UserGroups', 'decline-edit', '1', 1),
       ('Approve Deactivated UserGroups', 'approve-deactivation', '1', 1),
       ('Decline Deactivated UserGroups', 'decline-deactivation', '1', 1),
       ('Delete UserGroups', 'delete', '1', 1),
       ('Activate UserGroups', 'activate', '1', 1),
       ('View Users','DEFAULT','1',1),
       ('View Active Users', 'view-active','1',1),
       ('View New Users', 'view-new', '1',1),
       ('View Edited Users', 'view-edit', '1',1),
       ('View Deactivated Users', 'view-deactivated', '1', 1),
       ('View Inactive Users', 'view-inactive','1',1),
       ('Create Users', 'new','1' ,1),
       ('Edit Users', 'edit','1',1),
       ('Approve New  Users', 'approve-new', '1', 1),
       ('Decline New Users', 'decline-new', '1', 1),
       ('Approve Edited Users', 'approve-edit', '1', 1),
       ('Decline Edited Users', 'decline-edit', '1', 1),
       ('Approve Deactivated Users', 'approve-deactivation', '1', 1),
       ('Decline Deactivated Users', 'decline-deactivation', '1', 1),
       ('Delete Users', 'delete', '1', 1),
       ('Activate Users', 'activate', '1', 1);


DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
                              `id` INT(11) NOT NULL AUTO_INCREMENT,
                              `name` VARCHAR (45) NULL DEFAULT NULL,
                              `description` VARCHAR (100) NULL DEFAULT NULL ,
                              `base_type` VARCHAR (100) NULL DEFAULT NULL ,
                              `flag` VARCHAR (30) NULL DEFAULT NULL ,
                              `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                              `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                              `created_by` INT(11) NULL DEFAULT NULL ,
                              `last_modified_by` INT(11) NULL DEFAULT NULL ,

                              PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO  `user_group` (name, description, base_type, flag)
VALUES ('Super-Admin','super admin','super-admin','1');

DROP TABLE IF EXISTS `user_types`;
CREATE TABLE `user_types`(
                             `id` INT(11) NOT NULL AUTO_INCREMENT,
                             `name` VARCHAR (45) NULL DEFAULT NULL,
                             `code` VARCHAR (45) NULL DEFAULT NULL ,

                             PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO `user_types` (name, code)
VALUES ('SUPER-ADMIN','super admin'),
       ('AGENT','agent'),
       ('STUDENT','student');

DROP TABLE IF EXISTS `user_group_permissions`;
CREATE TABLE `user_group_permissions`(
                                         `permission_id` INT(11) NOT NULL ,
                                         `user_group_id` INT(11) NOT NULL ,

                                         PRIMARY KEY (`permission_id`),
                                         KEY `user_group_id` (`user_group_id`),
                                         CONSTRAINT `user_group_permissions_fk_1` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`),
                                         CONSTRAINT `user_group_permissions_fk_2` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO `user_group_permissions`  (permission_id, user_group_id)
VALUES ('1', '1'),
       ('2', '1'),
       ('3', '1'),
       ('4', '1'),
       ('5', '1'),
       ('6', '1'),
       ('7', '1'),
       ('8', '1'),
       ('9', '1'),
       ('10', '1'),
       ('11', '1'),
       ('12', '1'),
       ('13', '1'),
       ('14', '1'),
       ('15', '1'),
       ('16', '1'),
       ('17', '1'),
       ('18', '1'),
       ('19', '1'),
       ('20', '1'),
       ('21', '1'),
       ('22', '1'),
       ('23', '1'),
       ('24', '1'),
       ('25', '1'),
       ('26', '1'),
       ('27', '1'),
       ('28', '1'),
       ('29', '1'),
       ('30', '1'),
       ('31', '1'),
       ('32', '1');


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
                        `id` INT(11) NOT NULL AUTO_INCREMENT,
                        `first_name` VARCHAR (50) NULL DEFAULT NULL ,
                        `middle_name` VARCHAR (50) NULL DEFAULT NULL ,
                        `surname`     VARCHAR (50) NULL DEFAULT NULL ,
                        `enabled`     tinyINT(4)      DEFAULT '1',
                        `email`    VARCHAR (100) NULL DEFAULT NULL ,
                        `password` VARCHAR (250) NULL DEFAULT NULL ,
                        `phone`    VARCHAR (13) NULL DEFAULT NULL ,
                        `user_type_id` INT(11) NULL DEFAULT NULL ,
                        `user_group_id` INT(11) NULL DEFAULT NULL ,
                        `flag` VARCHAR (30) NULL DEFAULT NULL ,
                        `created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                        `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
                        `created_by` INT(11) NULL DEFAULT NULL ,
                        `last_modified_by` INT(11) NULL DEFAULT NULL ,

                        PRIMARY KEY (`id`),
                        KEY  `user_type_id` (`user_type_id`),
                        KEY  `user_group_id` (`user_group_id`),
                        CONSTRAINT `user_fk_1` FOREIGN KEY (`user_type_id`) REFERENCES `user_types` (`id`),
                        CONSTRAINT `user_fk_2` FOREIGN KEY  (`user_group_id`) REFERENCES `user_group` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO `users` (first_name, middle_name, surname, enabled, email, password, phone, user_type_id, user_group_id)
VALUES ('Taita','tavita','',1,'taitatavita@live.com','$2a$10$W1HpQtFzIBi5kTzt00gnrejQwF3O1xsrpZ92g0flNFskCZyY9R1bq','+254700000000',1,1);

set foreign_key_checks = 1;