package io.sn.mpisi2.models;

import java.util.ArrayList;

public class Course {
    private int id;
    private String name;
    private ArrayList<Student> studentsList = new ArrayList<>();

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    //Déclaration des methodes
}
