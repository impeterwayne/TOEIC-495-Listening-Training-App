package com.example.a900toeic.Model;

import java.util.Objects;

public class Answer {
    private long answerNum;
    private String correctKey;
    private String keyClick;


    public Answer() {
    }

    public Answer(long answerNum, String correctKey, String keyClick) {
        this.answerNum = answerNum;
        this.correctKey = correctKey;
        this.keyClick = keyClick;
    }

    public long getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(long answerNum) {
        this.answerNum = answerNum;
    }

    public String getCorrectKey() {
        return correctKey;
    }

    public void setCorrectKey(String correctKey) {
        this.correctKey = correctKey;
    }

    public String getKeyClick() {
        return keyClick;
    }

    public void setKeyClick(String keyClick) {
        this.keyClick = keyClick;
    }
}
