package com.example.schoolmanagement.service;
import com.example.schoolmanagement.model.Classes;
import com.example.schoolmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    List<Student> student = new ArrayList<>();

    public StudentService() {
        ArrayList<Classes> classList1 = new ArrayList<>();
        ArrayList<Classes> classList2 = new ArrayList<>();
        ArrayList<Classes> classList3 = new ArrayList<>();
        ArrayList<Classes> classList4 = new ArrayList<>();
        this.student.addAll(
                List.of(
                        new Student("1","Ali",21,classList1,"Sadri","CS"),
                        new Student("2","Dina",19,classList2,"Khadija","History"),
                        new Student("3","Nadia",18,classList3,"Ahmed","Math"),
                        new Student("4","Joshua",22,classList4,"Salah","CS")
                ));
    }
    public List<Student> getStudents(){
        return student;
    }

    public void update(Student student, Student p){
        student.setName(p.getName());
        student.setAge(p.getAge());
        student.setAdvisorName(p.getAdvisorName());
        student.setMajor(p.getMajor());
    }

    public boolean deleteStudent(String id){
        if (isStudentByID(id)){
            Student student = getById(id);
            getStudents().remove(student);
            return true;
        }
        return false;
    }

    public boolean isStudentByID(String id){
        int checkForWork = -1;
        Student student = getById(id);
        if (student != null){
            checkForWork = Integer.parseInt(id);
        }
        return (checkForWork == -1) ? false :  true;
    }

    public boolean addStudent(Student student){
        return this.student.add(student);
    }

    public  Student getById(String id){
        for (Student student: this.student) {
            if (student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    public void updateMajor(Student student, Student p) {
        student.setMajor(p.getMajor());
        ArrayList<Classes> newClassList = new ArrayList<>();
        student.setClassList(newClassList);

    }
}
