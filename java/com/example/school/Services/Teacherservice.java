package com.example.school.Services;

import com.example.school.Models.Classes;
import com.example.school.Models.Student;
import com.example.school.Models.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@AllArgsConstructor
public class Teacherservice {
    private ArrayList<Teacher> teachers=new ArrayList<>();
    private final Classservice classservice;



    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }


    public boolean isAdd(Teacher teacher) {
        return teachers.add(teacher);
    }

    public boolean isUpdate(Teacher teacher1) {
        for (int i = 0; i < teachers.size() ; i++) {
            if (teachers.get(i).getTeacherId().equals(teacher1.getTeacherId()))
                teachers.set(i,teacher1);
            return true;
        }
        return false;
    }

    public boolean isDelete(Teacher teacher) {
        return teachers.remove(teacher);


    }


//    public void addCourses(String teachertId,String courseId){
//        Classes currentclass= classservice.getClasses(courseId);
//        Teacher currentTeacher=getTeacher(teachertId);
//
//        currentTeacher.getClasses().add(currentclass);
//
//
//    }


    public Integer addCourses(String teachertId,String courseId){
        Classes currentclass= classservice.getClasses(courseId);
        Teacher currentTeacher=getTeacher(teachertId);
        if (currentTeacher==null)
            return -1;
        if (currentclass==null)
            return 0;

        currentTeacher.getClasses().add(currentclass);
        return 2;

    }

    public Teacher getTeacher(String teachertID){
        for (Teacher teacher:teachers ) {
            if (teacher.getTeacherId().equals(teachertID))
                return teacher;
        }
        return null;
    }

    public String TeacherName(String classid) {
        for (Teacher teacher:teachers ) {
           String Teachername= teacher.getTeachername(classid);
            if (Teachername!=null)
                return Teachername;

        }

       return null;
    }
}
