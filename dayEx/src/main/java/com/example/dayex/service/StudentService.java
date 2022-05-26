package com.example.dayex.service;

import com.example.dayex.model.Classes;
import com.example.dayex.model.Student;
import com.example.dayex.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ClassesService classesService;
    public boolean updateStudent(Student student){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId()))
                students.set(i,student);
            return true;
        }
        return false;
    }
    public boolean deleteStudent(String id){
        Student student = getStudentById(id);
        if(student == null){
            return false;
        }
        students.remove(student);
        return true;
    }
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students;
    }
    public boolean addStudent(Student student){
       return students.add(student);
    }

    public Student getStudentById(String id){
        for (Student student1: students) {
            if(student1.getId().equals(id))
                return student1;
        }

        return null;
    }
    public Integer addStudentToClass(String studentId,String classID){
        Student student = getStudentById(studentId);
        if(student == null){
            return -1;//Student not found
        }
        Classes classes = classesService.getClassesById(classID);
        if(classes== null){
            return 0;//class not found
        }
        ArrayList<Classes> classes1 = new ArrayList<>();
        classes1.add(classes);
        student.setClassList(classes1);
        return 1;//student added to class

    }
    public boolean changeMajor(String studentID,String major){
        Student student = getStudentById(studentID);
        if(student == null){
            return false;//Student not found
        }
        student.setMajor(major);
        student.setClassList(null);
        return true;
    }
    public ArrayList<Student> getStudentsClasses(String classID){
        ArrayList<Student> students1 = new ArrayList<>();
        for(int i = 0; i < students.size(); i++){
            Student student = students.get(i);//teacher(0) -> id: = 14, name:teacher1
            //teacher(0).classList -> currentTCL<Classes>
            ArrayList<Classes> currentStudentClassList =student.getClassList();//teacher(0).classList
            for(int j = 0; j < currentStudentClassList.size(); j++){
                if(currentStudentClassList.get(j).getId().equals(classID)){
                    students1.add(student);
                }
            }

        }
        return students1;
    }
}
