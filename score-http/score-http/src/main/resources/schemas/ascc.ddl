CREATE TABLE `ascc`
(
    `ascc_id`               bigint(20) unsigned          NOT NULL AUTO_INCREMENT COMMENT 'An internal, primary database key of an ASCC.',
    `guid`                  char(32) CHARACTER SET ascii NOT NULL COMMENT 'A globally unique identifier (GUID).',
    `cardinality_min`       int(11)                      NOT NULL COMMENT 'Minimum occurrence of the TO_ASCCP_ID. The valid values are non-negative integer.',
    `cardinality_max`       int(11)                      NOT NULL COMMENT 'Maximum cardinality of the TO_ASCCP_ID. A valid value is integer -1 and up. Specifically, -1 means unbounded. 0 means prohibited or not to use.',
    `seq_key`               int(11)                               DEFAULT NULL COMMENT '@deprecated since 2.0.0. This indicates the order of the associations among other siblings. A valid value is positive integer. The SEQ_KEY at the CC side is localized. In other words, if an ACC is based on another ACC, SEQ_KEY of ASCCs or BCCs of the former ACC starts at 1 again.',
    `from_acc_id`           bigint(20) unsigned          NOT NULL COMMENT 'FROM_ACC_ID is a foreign key pointing to an ACC record. It is basically pointing to a parent data element (type) of the TO_ASCCP_ID.',
    `to_asccp_id`           bigint(20) unsigned          NOT NULL COMMENT 'TO_ASCCP_ID is a foreign key to an ASCCP table record. It is basically pointing to a child data element of the FROM_ACC_ID. ',
    `den`                   varchar(200)                 NOT NULL COMMENT 'DEN (dictionary entry name) of the ASCC. This column can be derived from Qualifier and OBJECT_CLASS_TERM of the FROM_ACC_ID and DEN of the TO_ASCCP_ID as Qualifier + "_ " + OBJECT_CLASS_TERM + ". " + DEN. ',
    `definition`            text COMMENT 'This is a documentation or description of the ASCC. Since ASCC is business context independent, this is a business context independent description of the ASCC. Since there are definitions also in the ASCCP (as referenced by the TO_ASCCP_ID column) and the ACC under that ASCCP, definition in the ASCC is a specific description about the relationship between the ACC (as in FROM_ACC_ID) and the ASCCP.',
    `definition_source`     varchar(100)                          DEFAULT NULL COMMENT 'This is typically a URL identifying the source of the DEFINITION column.',
    `is_deprecated`         tinyint(1)                   NOT NULL DEFAULT '0' COMMENT 'Indicates whether the CC is deprecated and should not be reused (i.e., no new reference to this record should be created).',
    `replacement_ascc_id`   bigint(20) unsigned                   DEFAULT NULL COMMENT 'This refers to a replacement if the record is deprecated.',
    `created_by`            bigint(20) unsigned          NOT NULL COMMENT 'A foreign key to the APP_USER table referring to the user who creates the entity.\n\nThis column never change between the history and the current record for a given revision. The history record should have the same value as that of its current record.',
    `owner_user_id`         bigint(20) unsigned          NOT NULL COMMENT 'Foreign key to the APP_USER table. This is the user who owns the entity, is allowed to edit the entity, and who can transfer the ownership to another user.\n\nThe ownership can change throughout the history, but undoing shouldn''t rollback the ownership. ',
    `last_updated_by`       bigint(20) unsigned          NOT NULL COMMENT 'A foreign key to the APP_USER table referring to the last user who has updated the record. \n\nIn the history record, this should always be the user who is editing the entity (perhaps except when the ownership has just been changed).',
    `creation_timestamp`    datetime(6)                  NOT NULL COMMENT 'Timestamp when the revision of the ASCC was created. \n\nThis never change for a revision.',
    `last_update_timestamp` datetime(6)                  NOT NULL COMMENT 'The timestamp when the record was last updated.\n\nThe value of this column in the latest history record should be the same as that of the current record. This column keeps the record of when the change has occurred.',
    `state`                 varchar(20)                           DEFAULT NULL COMMENT 'Deleted, WIP, Draft, QA, Candidate, Production, Release Draft, Published. This the revision life cycle state of the BCC.\n\nState change can''t be undone. But the history record can still keep the records of when the state was changed.',
    `prev_ascc_id`          bigint(20) unsigned                   DEFAULT NULL COMMENT 'A self-foreign key to indicate the previous history record.',
    `next_ascc_id`          bigint(20) unsigned                   DEFAULT NULL COMMENT 'A self-foreign key to indicate the next history record.',
    PRIMARY KEY (`ascc_id`),
    KEY `ascc_from_acc_id_fk` (`from_acc_id`),
    KEY `ascc_to_asccp_id_fk` (`to_asccp_id`),
    KEY `ascc_created_by_fk` (`created_by`),
    KEY `ascc_owner_user_id_fk` (`owner_user_id`),
    KEY `ascc_last_updated_by_fk` (`last_updated_by`),
    KEY `ascc_prev_ascc_id_fk` (`prev_ascc_id`),
    KEY `ascc_next_ascc_id_fk` (`next_ascc_id`),
    KEY `ascc_guid_idx` (`guid`),
    KEY `ascc_last_update_timestamp_desc_idx` (`last_update_timestamp`),
    KEY `ascc_replacement_ascc_id_fk` (`replacement_ascc_id`),
    CONSTRAINT `ascc_created_by_fk` FOREIGN KEY (`created_by`) REFERENCES `app_user` (`app_user_id`),
    CONSTRAINT `ascc_from_acc_id_fk` FOREIGN KEY (`from_acc_id`) REFERENCES `acc` (`acc_id`),
    CONSTRAINT `ascc_last_updated_by_fk` FOREIGN KEY (`last_updated_by`) REFERENCES `app_user` (`app_user_id`),
    CONSTRAINT `ascc_next_ascc_id_fk` FOREIGN KEY (`next_ascc_id`) REFERENCES `ascc` (`ascc_id`),
    CONSTRAINT `ascc_owner_user_id_fk` FOREIGN KEY (`owner_user_id`) REFERENCES `app_user` (`app_user_id`),
    CONSTRAINT `ascc_prev_ascc_id_fk` FOREIGN KEY (`prev_ascc_id`) REFERENCES `ascc` (`ascc_id`),
    CONSTRAINT `ascc_replacement_ascc_id_fk` FOREIGN KEY (`replacement_ascc_id`) REFERENCES `ascc` (`ascc_id`),
    CONSTRAINT `ascc_to_asccp_id_fk` FOREIGN KEY (`to_asccp_id`) REFERENCES `asccp` (`asccp_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='An ASCC represents a relationship/association between two ACCs through an ASCCP. ';