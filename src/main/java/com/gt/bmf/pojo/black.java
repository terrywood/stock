package com.gt.bmf.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Riky on 2016/3/5.
 */
@Entity
public class Black {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    public Black(String code) {
        this.id = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
       // Black black =(Black)obj;
        return ((Black) obj).getId().equals(id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
