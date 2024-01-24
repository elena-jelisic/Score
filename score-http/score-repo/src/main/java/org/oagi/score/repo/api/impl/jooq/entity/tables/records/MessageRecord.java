/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Message;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MessageRecord extends UpdatableRecordImpl<MessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.message.message_id</code>.
     */
    public void setMessageId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.message.message_id</code>.
     */
    public ULong getMessageId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.message.sender_id</code>. The user who created this
     * record.
     */
    public void setSenderId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.message.sender_id</code>. The user who created this
     * record.
     */
    public ULong getSenderId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.message.recipient_id</code>. The user who is a
     * target to possess this record.
     */
    public void setRecipientId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.message.recipient_id</code>. The user who is a
     * target to possess this record.
     */
    public ULong getRecipientId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.message.subject</code>. A subject of the message
     */
    public void setSubject(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.message.subject</code>. A subject of the message
     */
    public String getSubject() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oagi.message.body</code>. A body of the message.
     */
    public void setBody(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.message.body</code>. A body of the message.
     */
    public String getBody() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oagi.message.body_content_type</code>. A content type of
     * the body
     */
    public void setBodyContentType(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.message.body_content_type</code>. A content type of
     * the body
     */
    public String getBodyContentType() {
        return (String) get(5);
    }

    /**
     * Setter for <code>oagi.message.is_read</code>. An indicator whether this
     * record is read or not.
     */
    public void setIsRead(Byte value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.message.is_read</code>. An indicator whether this
     * record is read or not.
     */
    public Byte getIsRead() {
        return (Byte) get(6);
    }

    /**
     * Setter for <code>oagi.message.creation_timestamp</code>. The timestamp
     * when the record was first created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.message.creation_timestamp</code>. The timestamp
     * when the record was first created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MessageRecord
     */
    public MessageRecord() {
        super(Message.MESSAGE);
    }

    /**
     * Create a detached, initialised MessageRecord
     */
    public MessageRecord(ULong messageId, ULong senderId, ULong recipientId, String subject, String body, String bodyContentType, Byte isRead, LocalDateTime creationTimestamp) {
        super(Message.MESSAGE);

        setMessageId(messageId);
        setSenderId(senderId);
        setRecipientId(recipientId);
        setSubject(subject);
        setBody(body);
        setBodyContentType(bodyContentType);
        setIsRead(isRead);
        setCreationTimestamp(creationTimestamp);
        resetChangedOnNotNull();
    }
}
