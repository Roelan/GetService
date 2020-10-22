package com.example.getservice.data;

public class NoteData {

    private String message;
    private String time;

    public NoteData(String message, String time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
