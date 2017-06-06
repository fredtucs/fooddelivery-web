package org.wifry.fooddelivery.fcm.model;

public class Notification {

    private String sound;

    private String title;

    private String body;

    public Notification() {
    }

    public Notification(String sound, String title, String body) {
        this.sound = sound;
        this.title = title;
        this.body = body;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
