package com.liu.util;


@table(name = "t2")
public class t2 {
    @Column(name = "id")
    private Integer id;
    private String name;

    public t2() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "t2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
