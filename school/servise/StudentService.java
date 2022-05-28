package com.example.school.servise;

import com.example.school.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service @RequiredArgsConstructor
public class StudentService {
    private final ArrayList<Student> students = new ArrayList<>();
    private final AdvisorService advisorService;
    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean updateStudent(Integer index, Student student) {
        students.set(index,student);
        return true;
    }

    public boolean deleteStudent(String studentId) {
        students.remove(studentId);
        return true;
    }

}
