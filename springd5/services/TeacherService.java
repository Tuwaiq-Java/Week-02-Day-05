package com.example.springd5.services;

import com.example.springd5.model.Advisor;
import com.example.springd5.model.Classes;
import com.example.springd5.model.Student;
import com.example.springd5.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TeacherService {
    private ArrayList<Teacher> teachers=new ArrayList<>();
    private final ClassesService classesService;
    private final TeacherService teacherService;

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    public boolean addTeacher(Teacher teacher){
        return teachers.add(teacher);
    }

    public boolean updateTeacher(Integer index,Teacher teacher){

        Teacher currentTeacher =teachers.set(index,teacher);
        if(index>teachers.size()-1 || index<0){
            return false;
        }
        return true;
    }

    public boolean deleteTeacher(Integer index){
        if(index>teachers.size()-1 || index<0){
            return false;
        }
        teachers.remove(index);
        return true;
    }

    public Teacher getTeacher(String teacherid){
        for(Teacher teacher:teachers){
            if(teacher.getId().equals(teacherid)){
                return teacher;
            }
        }
        return null;
    }
    public int addClasses(String teacherid , String classesid){
        Teacher teacher=getTeacher(teacherid);
        if(teacher==null){
            return -1;
        }
        Classes classes =classesService.getClass(classesid);
        if(classes==null){
            return 0;
        }
        ArrayList<Classes> teacherClass= teacher.getClassList();
        teacherClass.add(classes);
        teacher.setClassList(teacherClass);
        return 1;
    }

    
    public  Teacher teacherClass(String classid) {
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            ArrayList<Classes> currentTeacher = teacher.getClassList();
            for (int j = 0; j < currentTeacher.size(); j++) {
                if (currentTeacher.get(j).getId().equals(classid)) {
                    return teacher;
                }
            }
        }
        return null;
    }


    public String getTeacherName(String id) {
        Classes classes=classesService.getClass(id);
        if(classes==null){
            return "dont have teacher with this id";
        }
        Teacher teacher = teacherClass(id);
        if (teacher.getId().equals(id)){
            return teacher.getName();
        }
        return "dont have teacher ";
    }
}
