package com.example.springday05.service;

import com.example.springday05.model.Classes;
import com.example.springday05.model.Student;
import com.example.springday05.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {

    public ArrayList<Student> students = new ArrayList<>();
    public final ClassesService classesService;
    public final AdvisorService advisorService;

    public ArrayList<Student> getStudent() {
        return students;
    }

    public String addStudent(Student student) {
        students.add(student);
        return "Student add !";
    }

    public String editStudent(int index, Student student) {
        students.set(index,student);
        return "Student edit";
    }

    public String deleteStudent(int index) {
        students.remove(index);
        return "Student deleted";
    }

    public int addStudentClass(String studintID, String classID) {
        Student student = checkStudent(studintID);
        if(student == null){
            return -1;
        }
        Classes classes = classesService.checkClass(classID);
        if(classes == null){
            return 0;
        }
        ArrayList<Classes> studentClass = student.getClassList();
        studentClass.add(classes);
        student.setClassList(studentClass);
        return 1;

    }

    public Student checkStudent(String studentID){
        for (Student student: students){
            if (student.getId().equals(studentID)){
                return student;
            }
        }
        return null;
    }

    public int changeMajor(String studentID, String major) {
        Student student = checkStudent(studentID);
        if(student == null){
            return 0;
        }
        student.setMajor(major);
        student.setClassList(new ArrayList<>());
        return 1;

    }

    public String getStudentName(String id) {
        Classes classes = classesService.checkClass(id);
        if (classes == null){
            return "Non";
        }
        Student student = getStudentByClassID(id);
        if (student.getId().equals(id)){
            return student.getName();
        }
        return "Non";


    }

    public Student getStudentByClassID(String id){
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            ArrayList<Classes> currentStudent = student.getClassList();
            for (int j = 0; j < currentStudent.size(); j++) {
                if (currentStudent.get(j).getId().equals(id)){
                    return student;
                }
            }
        }
        return null;
    }

    public ArrayList<Student> getStudenList(String advisorID){
        String advisorName = advisorService.getAdvisorName(advisorID);
        ArrayList<Student> newStudent = new ArrayList<>();
        for (int i = 0; i <students.size() ; i++) {
            if (students.get(i).getAdvisorName().equals(advisorName)){
                newStudent.add(students.get(i));
            }
        }
        return newStudent;
    }
}
