ALTER TABLE `user_group_permissions`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`permission_id`, `user_group_id`);
;
