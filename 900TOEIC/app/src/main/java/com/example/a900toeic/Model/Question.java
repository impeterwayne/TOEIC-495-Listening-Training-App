package com.example.a900toeic.Model;

public class Question {
    private  String id;
    private  String audio_url;
    public Question(){

    }
    public Question( String id, String audio_url) {
        this.id = id;
        this.audio_url = audio_url;

    }

    public String getId() {
        return id;
    }

    public String getAudio_url() {
        return audio_url;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }


}
