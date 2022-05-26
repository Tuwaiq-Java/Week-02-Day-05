package com.example.excercise9.service;

import com.example.excercise9.model.Course;
import com.example.excercise9.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TeacherService {

    private ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {

        return teachers;
    }

    public boolean isAdd(Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).equals(teacher)){
                return false;
            }
        }
        teachers.add(teacher);
        return true;
    }

    public boolean isUpdate(Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if ((teachers.get(i).getId().equals(teacher.getId()))) {
                teachers.add(teacher);
                return true;
            }
        }
        return false;
    }

    public boolean isDelete(Teacher teacher) {

        return teachers.remove(teacher);
    }

    public Teacher teacherName(String name) {

        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
               return teacher;
            }
            }
        return null;
        }

    public Teacher getTeacher(String teacherId){
        for(Teacher teacher : teachers){
            if(teacher.getId().equals(teacherId)){
                return teacher;
            }
        }
        return null;
    }
}
