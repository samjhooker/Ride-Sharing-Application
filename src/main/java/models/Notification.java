package models;

import java.util.UUID;

/**
 * Created by samuelhooker on 13/05/17.
 */
public class Notification {
    public Notification(String message, UUID userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public UUID getUserId() {
        return userId;
    }

    private String message;
    private UUID userId;
}
