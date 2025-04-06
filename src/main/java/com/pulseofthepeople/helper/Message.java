package com.pulseofthepeople.helper;

import lombok.*;

@Getter
@Setter
public class Message {
    private String content;
    private MessageType type = MessageType.blue;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Explicitly add getter for type
    public MessageType getType() {
        return this.type;
    }

    // Private constructor for the builder
    private Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    // Empty constructor
    public Message() {
    }

    // Static method to create a builder
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    // Builder class
    public static class MessageBuilder {
        private String content;
        private MessageType type = MessageType.blue;

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder type(MessageType type) {
            this.type = type;
            return this;
        }

        public Message build() {
            return new Message(content, type);
        }
    }
}