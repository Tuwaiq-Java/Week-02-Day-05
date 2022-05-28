package com.example.school.servise;

import com.example.school.model.Student;
import com.example.school.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service @RequiredArgsConstructor
public class TeacherService {
    private ArrayList<Teacher> teachers = new ArrayList<>();
    
    public  ArrayList<Teacher> getTeacher() {
        return teachers;
    }

    public boolean addTeacher(Teacher teacher) {
        return teachers.add(teacher);
    }
    public boolean updateTeacher(Integer index ,Teacher teacher) {
        teachers.set(index,teacher);
        return true;
    }

    public boolean deleteTeacher(String teacherId) {
        teachers.remove(teacherId);
        return true;
    }
    ////////=============////////
}
