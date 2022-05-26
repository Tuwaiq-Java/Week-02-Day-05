package com.demo.Ex5W2.Service;

import com.demo.Ex5W2.Model.Class;
import com.demo.Ex5W2.Model.Student;
import com.demo.Ex5W2.Service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private ArrayList<Student> studentList = new ArrayList();
    private final ClassService classService;

    public ArrayList<Student> getStudents(){
        return studentList;
    }

    public boolean addStudents(Student student) {
        return studentList.add(student);
    }

    public boolean updateStudents(int index, Student student) {
        if (index >= studentList.size() || index < 0) {
            return false;
        }
        studentList.set(index, student);
        return true;
    }

    public boolean removeStudents(int index) {
        if(index > studentList.size() - 1){
            return false;
        }
        studentList.remove(index);
        return true;
    }

    public Student getStudent(String id){
        for (Student student:studentList) {
            if(student.getStudentID().equals(id)){
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getClassList(String studentID,Class ClassID){
        ArrayList<Student>  classList = new ArrayList<>();
        for (Student student: classList) {
            if(student.getClassList().equals(studentID)){
                student.getStudentID();
            }
            if (student.getClassList().equals(ClassID)){
                student.getStudentID();
            }
        }
        return null;
    }
}
