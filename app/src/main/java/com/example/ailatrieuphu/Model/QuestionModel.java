package com.example.ailatrieuphu.Model;

public class QuestionModel {
    private String question, casea, caseb, casec, cased;
    private int _id, level, truecase;

    public QuestionModel() {
    }

    public QuestionModel(String question, String casea, String caseb, String casec, String cased, int _id, int level, int truecase) {
        this.question = question;
        this.casea = casea;
        this.caseb = caseb;
        this.casec = casec;
        this.cased = cased;
        this._id = _id;
        this.level = level;
        this.truecase = truecase;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCasea() {
        return casea;
    }

    public void setCasea(String casea) {
        this.casea = casea;
    }

    public String getCaseb() {
        return caseb;
    }

    public void setCaseb(String caseb) {
        this.caseb = caseb;
    }

    public String getCasec() {
        return casec;
    }

    public void setCasec(String casec) {
        this.casec = casec;
    }

    public String getCased() {
        return cased;
    }

    public void setCased(String cased) {
        this.cased = cased;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTruecase() {
        return truecase;
    }

    public void setTruecase(int truecase) {
        this.truecase = truecase;
    }
}
