package com.gt.bmf.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class HuDong implements Serializable {

    private static final long serialVersionUID = -1686320397097241613L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String code;
    private String name;
    private String question;
    private Date questionDate;


    @org.hibernate.annotations.Type(type = "yes_no")
    private Boolean isGuDong;

    private String answer;
    private Date answerDate;
    private String status;


    public Integer getMarkCount() {
        return markCount;
    }

    public void setMarkCount(Integer markCount) {
        this.markCount = markCount;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    private Integer markCount;
    private Date  markDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getGuDong() {
        return isGuDong;
    }

    public void setGuDong(Boolean guDong) {
        isGuDong = guDong;
    }
    //private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }
}
