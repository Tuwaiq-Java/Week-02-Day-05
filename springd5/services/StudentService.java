package com.example.springd5.services;

import com.example.springd5.model.Api;
import com.example.springd5.model.Classes;
import com.example.springd5.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class StudentService {
    private ArrayList<Student> students=new ArrayList<>();
    private final ClassesService classesService;

    public ArrayList<Student> getStudent(){
        return students;
    }

    public boolean addStudent(Student student){
        return students.add(student);
    }

    public boolean updateStudent(Integer index,Student student){

        Student currentStudent =students.set(index,student);
        if(index>students.size()-1 || index<0){
            return false;
        }
        return true;
    }

    public boolean deleteStudent(Integer index){
        if(index>students.size()-1 || index<0){
            return false;
        }
        students.remove((int)index);
        return true;
    }

    public Student getStudent(String studentid){
        for(Student student:students){
            if(student.getId().equals(studentid)){
                return student;
            }
        }
        return null;
    }

    public Student getSMajor(String major){
        for(Student student:students){
            if(student.getMajor().equals(major)){
                return student;
            }
        }
        return null;
    }

    public int addClasses(String studentid , String classesid){
        Student student=getStudent(studentid);
        if(student==null){
            return -1;
        }
        Classes classes =classesService.getClass(classesid);
        if(classes==null){
            return 0;
        }
        ArrayList<Classes> studentClass= student.getClassList();
        studentClass.add(classes);
        student.setClassList(studentClass);
        return 1;
    }

    public int changeMajor(String studentid,String major){
       Student student =getStudent(studentid);
        if(student==null){
            return 0;
        }
        student.setMajor(major);
        students.clear();//student.setClassList(new ArrayList<>());
        return 1;
    }
}
