CREATE TABLE `code_list_value_manifest`
(
    `code_list_value_manifest_id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `release_id`                              bigint(20) unsigned NOT NULL,
    `code_list_value_id`                      bigint(20) unsigned NOT NULL,
    `code_list_manifest_id`                   bigint(20) unsigned NOT NULL,
    `based_code_list_value_manifest_id`       bigint(20) unsigned          DEFAULT NULL,
    `conflict`                                tinyint(1)          NOT NULL DEFAULT '0' COMMENT 'This indicates that there is a conflict between self and relationship.',
    `replacement_code_list_value_manifest_id` bigint(20) unsigned          DEFAULT NULL COMMENT 'This refers to a replacement manifest if the record is deprecated.',
    `prev_code_list_value_manifest_id`        bigint(20) unsigned          DEFAULT NULL,
    `next_code_list_value_manifest_id`        bigint(20) unsigned          DEFAULT NULL,
    PRIMARY KEY (`code_list_value_manifest_id`),
    KEY `code_list_value_manifest_code_list_value_id_fk` (`code_list_value_id`),
    KEY `code_list_value_manifest_release_id_fk` (`release_id`),
    KEY `code_list_value_manifest_code_list_manifest_id_fk` (`code_list_manifest_id`),
    KEY `code_list_value_manifest_prev_code_list_value_manifest_id_fk` (`prev_code_list_value_manifest_id`),
    KEY `code_list_value_manifest_next_code_list_value_manifest_id_fk` (`next_code_list_value_manifest_id`),
    KEY `code_list_value_replacement_code_list_value_manifest_id_fk` (`replacement_code_list_value_manifest_id`),
    KEY `code_list_value_manifest_based_code_list_value_manifest_id_fk` (`based_code_list_value_manifest_id`),
    CONSTRAINT `code_list_value_manifest_based_code_list_value_manifest_id_fk` FOREIGN KEY (`based_code_list_value_manifest_id`) REFERENCES `code_list_value_manifest` (`code_list_value_manifest_id`),
    CONSTRAINT `code_list_value_manifest_code_list_manifest_id_fk` FOREIGN KEY (`code_list_manifest_id`) REFERENCES `code_list_manifest` (`code_list_manifest_id`),
    CONSTRAINT `code_list_value_manifest_code_list_value_id_fk` FOREIGN KEY (`code_list_value_id`) REFERENCES `code_list_value` (`code_list_value_id`),
    CONSTRAINT `code_list_value_manifest_next_code_list_value_manifest_id_fk` FOREIGN KEY (`next_code_list_value_manifest_id`) REFERENCES `code_list_value_manifest` (`code_list_value_manifest_id`),
    CONSTRAINT `code_list_value_manifest_prev_code_list_value_manifest_id_fk` FOREIGN KEY (`prev_code_list_value_manifest_id`) REFERENCES `code_list_value_manifest` (`code_list_value_manifest_id`),
    CONSTRAINT `code_list_value_manifest_release_id_fk` FOREIGN KEY (`release_id`) REFERENCES `release` (`release_id`),
    CONSTRAINT `code_list_value_replacement_code_list_value_manifest_id_fk` FOREIGN KEY (`replacement_code_list_value_manifest_id`) REFERENCES `code_list_value_manifest` (`code_list_value_manifest_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;