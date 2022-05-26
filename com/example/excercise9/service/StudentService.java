package com.example.excercise9.service;

import com.example.excercise9.model.Student;
import com.example.excercise9.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {

        return students;
    }

    public boolean isAdd(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean isUpdate(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if ((students.get(i).equals(student))) {
                students.add(student);
                return true;
            }
        }
        return false;
    }

    public boolean isDelete(Student student) {
        return students.remove(student);
    }

    public Student getStudent(String studentId){
        for(Student student : students){
            if(student.getId().equals(studentId)){
                return student;
            }
        }
        return null;
    }


    public Integer changeMajor(String studentId, String major){

        Student currentStudent = getStudent(studentId);

        if(currentStudent == null){
            return -1;
        }
        if(currentStudent.getMajor().equals(major)){
            return 0;
        }

        currentStudent.setMajor(major);
        currentStudent.getCoursesList().remove(major);
        return 1;
    }


    public Student getStudents(String courseId){
        for(Student student : students){
            if(student.getId().equals(courseId)){
                return student;
            }
        }
        return null;
    }

    public Student getAdvisor(String advisorId){
        for(Student student : students){
            if(student.getId().equals(advisorId)){
                return student;
            }
        }
        return null;
    }



}