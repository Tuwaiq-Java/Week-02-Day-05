package com.example.springday05.service;

import com.example.springday05.model.Advisor;
import com.example.springday05.model.Classes;
import com.example.springday05.model.Student;
import com.example.springday05.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {

    public ArrayList<Teacher> teachers = new ArrayList<>();
    public final ClassesService classesService;


    public ArrayList<Teacher> getTeacher() {
        return teachers;
    }

    public String addTeacher(Teacher teacher) {
        teachers.add(teacher);
        return "Teacher add !";
    }

    public String editTeacher(int index, Teacher teacher) {
        teachers.set(index,teacher);
        return "Teacher edit completed !";
    }


    public String deleteTeacher(int index) {
        teachers.remove(index);
        return "Teacher delete completed !";
    }

    public int addTeacherClass(String teacherID, String classID) {
        Teacher teacher = checkTeacher(teacherID);
        if(teacher == null){
            return -1;
        }
        Classes classes = classesService.checkClass(classID);
        if(classes == null){
            return 0;
        }
        ArrayList<Classes> teacherClassList = teacher.getClassList();
        teacherClassList.add(classes);
        teacher.setClassList(teacherClassList);
        return 1;
    }
    public Teacher checkTeacher(String teacherID){
        for (Teacher teacher: teachers){
            if (teacher.getId().equals(teacherID)){
                return teacher;
            }
        }
        return null;
    }
      public String getTeacherName(String id){
        Classes classes = classesService.checkClass(id);
        if (classes == null){
            return "Non";
        }
        Teacher teacher = getTeacherByClassID(id);
        if (teacher.getId().equals(id)){
            return teacher.getName();
        }
        return "Non";


    }

    public Teacher getTeacherByClassID(String id){
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            ArrayList<Classes> currentTeacher = teacher.getClassList();
            for (int j = 0; j < currentTeacher.size(); j++) {
                if (currentTeacher.get(j).getId().equals(id)){
                    return teacher;
                }
            }
        }
        return null;
    }
}
/*
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
* */