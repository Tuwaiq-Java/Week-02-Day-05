package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Class;
import com.example.schoolmanagement.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private ArrayList<Student> students =new ArrayList<>();
    private final AdvisorService advisorService;

    public ArrayList<Student> getStudents(){
        return students;
    }


    public Boolean addStudent(Student student) {
        if(isStudentExists(student.getID())) {
            return false;
        }
        return students.add(student);

    }

    private boolean isStudentExists(String id) {
        for(Student student: students) {
            if (student.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Boolean editStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(student.getID())) {
                students.set(i,student);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteStudent(String studentID) {
        for(Student student: students) {
            if (student.getID().equals(studentID)) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public Student isStudentExist(String studentID) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(studentID)) {
                return students.get(i);
            }
        }
        return null;
    }

    public ArrayList<Student> getStudentsWithThisClass(String classID) {
        ArrayList<Student> studentWithThisClass= new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            ArrayList<Class> currStudentClassList= student.getClassList();
            for (int j = 0; j < currStudentClassList.size(); j++) {
                if (currStudentClassList.get(i).getID().equals(classID)) {
                    studentWithThisClass.add(student);
                }
            }
        }
        return studentWithThisClass;
    }

    public ArrayList<Student> getStudentsWithThisAdvisor(String advisorID) {
        ArrayList<Student> studentWithThisAdvisor= new ArrayList<>();
        ArrayList<Advisor> advisors = advisorService.getAdvisors();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            for (int j = 0; j < advisors.size(); j++) {
                if (student.getAdvisorName().equals(advisors.get(j).getName())){
                    studentWithThisAdvisor.add(student);
                }
            }
        }
        return studentWithThisAdvisor;
    }
}
