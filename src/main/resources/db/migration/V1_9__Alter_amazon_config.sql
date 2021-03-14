ALTER TABLE `amazon-config`
    CHANGE COLUMN `access-key` `access_key` VARCHAR(100) NULL DEFAULT NULL ,
    CHANGE COLUMN `secret-key` `secret_key` VARCHAR(100) NULL DEFAULT NULL ,
    CHANGE COLUMN `bucket-name` `bucket_name` VARCHAR(100) NULL DEFAULT NULL ;
