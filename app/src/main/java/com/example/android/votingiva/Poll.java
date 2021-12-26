package com.example.android.votingiva;

public class Poll {
    int id, C1, C2, C3, C4;
    String PollName,Question, op1, op2, op3, op4;

    public Poll(int id, String pollName,String question, String op1, String op2, String op3, String op4, int c1, int c2, int c3, int c4) {
        this.id = id;
        this.Question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.PollName = pollName;
        this.C1 = c1;
        this.C2 = c2;
        this.C3 = c3;
        this.C4 = c4;
    }

    public int getC1() {
        return C1;
    }

    public void setC1(int c1) {
        this.C1 = c1;
    }

    public int getC2() {
        return C2;
    }

    public void setC2(int c2) {
        this.C2 = c2;
    }

    public int getC3() {
        return C3;
    }

    public void setC3(int c3) {
        this.C3 = c3;
    }

    public int getC4() {
        return C4;
    }

    public void setC4(int c4) {
        this.C4 = c4;
    }

    public String getPollName() {
        return PollName;
    }

    public void setPollName(String pollName) {
        PollName = pollName;
    }

    public Poll(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }
}
