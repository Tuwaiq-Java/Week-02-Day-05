package com.example.dayex.service;

import com.example.dayex.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final ClassesService classesService;
    public boolean updateTeacher(Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(teacher.getId()))
                teachers.set(i,teacher);
            return true;
        }
        return false;
    }
    public boolean deleteTeacher(String id){
        Teacher teacher = getTeacherById(id);
        if(teacher == null){
            return false;
        }
        teachers.remove(teacher);
        return true;
    }
    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }
    public boolean addTeacher(Teacher teacher){
       return teachers.add(teacher);
    }

    public Teacher getTeacherById(String id){
        for (Teacher teacher: teachers) {
            if(teacher.getId().equals(id))
                return teacher;
        }

        return null;
    }
    public Integer addTeacherToClass(String teacherId,String classID){
        Teacher teacher = getTeacherById(teacherId);
        if(teacher == null){
            return -1;//Teacher not found
        }
        Classes classes = classesService.getClassesById(classID);
        if(classes== null){
            return 0;//class not found
        }
        ArrayList<Classes> classes1 = new ArrayList<>();
        classes1.add(classes);
        teacher.setClassList(classes1);
        return 1;//Teacher added to class

    }
    public Teacher getTeachers(String classID){
        for(int i = 0; i < teachers.size(); i++){
            Teacher teacher = teachers.get(i);//teacher(0) -> id: = 14, name:teacher1
            //teacher(0).classList -> currentTCL<Classes>

            ArrayList<Classes> currentTeacherClassList =teacher.getClassList();//teacher(0).classList
            for(int j = 0; j < currentTeacherClassList.size(); j++){
                if(currentTeacherClassList.get(j).getId().equals(classID)){
                    return teacher;
                }
            }
        }
        return null;
    }
}
