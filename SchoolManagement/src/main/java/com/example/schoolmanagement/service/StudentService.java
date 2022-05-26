package com.example.schoolmanagement.service;

import com.example.schoolmanagement.models.Classes;
import com.example.schoolmanagement.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final ClassService classService;
    private ArrayList<Student> students = new ArrayList<Student>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public Boolean updateStudent(Student student, Integer index){
        if(index > students.size()-1){
            return false;
        }
        students.set(index, student);
        return true;
    }
    public Boolean deleteStudent(Integer index){
        if(index > students.size()-1){
            return false;
        }
        students.remove(index);
        return true;
    }

    public ArrayList getStudentsByClassId(String classId){
        ArrayList<Student> stus = new ArrayList<>();
        for(Student student: students){
            if(student.getClassList().contains(findClass(classId))){
                stus.add(student);
            }
        }

        if(stus.size() == 0){
            return null;
        }
        return stus;
    }

    public Integer addClass(String classId, String stuId) {
        Student s = findStudent(stuId);
        Classes c = findClass(classId);
        if(s != null){
            if(c != null){
                s.addClass(c);
                return 0; //Class has been added
            }
            return -1; //Class not found
        }
        return 1; //Student not found
    }
    public Integer changeMajor(String stuId, String major){
        Student student = findStudent(stuId);
        if(student != null){
            student.setMajor(major);
            student.getClassList().clear();
            return 0;
        }
        return -1;
    }
    public Student findStudent(String stuId){
        for(Student student: students){
            if(student.getId().equals(stuId)){
                return student;
            }
        }
        return null;
    }
    public Classes findClass(String classId){
        for(Classes c: classService.getClasses()){
            if(c.getId().equals(classId)){
                return c;
            }
        }
        return null;
    }
}

