package com.example.schoolmanagement.service;

import com.example.schoolmanagement.models.Classes;
import com.example.schoolmanagement.models.Student;
import com.example.schoolmanagement.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class TeacherService {
    private ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public Boolean updateTeacher(Teacher teacher, Integer index){
        if(index > teachers.size()-1){
            return false;
        }
        teachers.set(index, teacher);
        return true;
    }
    public String findTeacherByClass(String classId){
        for(Teacher teacher: teachers){
            if(teacher.getClassList().contains(findClass(classId))){
                return teacher.getName();
            }
        }
        return "No teacher was found";
    }
    public Boolean deleteTeacher(Integer index){
        if(index > teachers.size()-1){
            return false;
        }
        teachers.remove(index);
        return true;
    }

    private final ClassService classService;

    public Integer addClass(String classId, String teacherId) {
        Teacher t = findTeacher(teacherId);
        Classes c = findClass(classId);
        if(t != null){
            if(c != null){
                t.addClass(c);
                return 0; //Class has been added
            }
            return -1; //Class not found
        }
        return 1; //Student not found
    }
    public Teacher findTeacher(String teacherId){
        for(Teacher teacher: teachers){
            if(teacher.getId().equals(teacherId)){
                return teacher;
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

