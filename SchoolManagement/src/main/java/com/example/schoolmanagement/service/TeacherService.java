package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Class;
import com.example.schoolmanagement.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    private ArrayList<Teacher> teachers =new ArrayList<>();

    public ArrayList<Teacher> getTeacher(){
        return teachers;
    }


    public Boolean addTeacher(Teacher teacher) {
        if(isTeacherExists(teacher.getID())) {
            return false;
        }
        return teachers.add(teacher);

    }

    private boolean isTeacherExists(String id) {
        for(Teacher teacher: teachers) {
            if (teacher.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Boolean editTeacher(Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getID().equals(teacher.getID())) {
                teachers.set(i,teacher);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteTeacher(String teacherID) {
        for(Teacher teacher: teachers) {
            if (teacher.getID().equals(teacherID)) {
                teachers.remove(teacher);
                return true;
            }
        }
        return false;
    }

    public Teacher isTeacherExist(String teacherID) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getID().equals(teacherID)) {
                return teachers.get(i);
            }
        }
        return null;
    }

    public Teacher getTeacherClass(String classID) {
        Teacher teacher ;
        ArrayList<Class> teacherClasses;
        for (int i = 0; i < teachers.size(); i++) {
            teacher = teachers.get(i);
            teacherClasses = teacher.getClassList();
            for (int j = 0; j < teacherClasses.size(); j++) {
                if (teacherClasses.get(j).getID().equals(classID)) {
                    return teacher;
                }
            }
        }

        return null;
    }
}
