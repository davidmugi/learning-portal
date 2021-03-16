ALTER TABLE `users`
    ADD COLUMN `grade_Id` INT NULL AFTER `last_modified_by`,
ADD INDEX `user_fk_3_idx` (`grade_Id` ASC) ;
;
ALTER TABLE `users`
    ADD CONSTRAINT `user_fk_3`
        FOREIGN KEY (`grade_Id`)
            REFERENCES `grade` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
