package com.example.schoolmanagementsoftware.service;

import com.example.schoolmanagementsoftware.model.Course;
import com.example.schoolmanagementsoftware.model.Student;
import com.example.schoolmanagementsoftware.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final CourseService courseService;
    ArrayList<Teacher> teachers=new ArrayList<>();

    public ArrayList<Teacher> getAllTeacher(){
        return this.teachers;
    }

    public boolean addTeacher(Teacher teacher){
        return teachers.add(teacher);
    }

    public boolean updateTeacher(Teacher teacher){
        if(teachers.set(getTeacher(teacher.getId()),teacher)!=null){
            return true;
        }
        return false;
    }

    public boolean deleteTeacher(String id){
        return teachers.remove(getTeacher(id));
    }

    public boolean addCourseToTeacher(String teacherId,String courseId){
        Teacher currentTeacher=teachers.get(getTeacher(teacherId));
        Course currentCourse=courseService.getAllcourse().get(getTeacher(courseId));
        if (currentTeacher!=null || currentCourse!=null){
            currentTeacher.getCourses().add(currentCourse);
        }
        return false;
    }

    public String getTeacherName(String courseId) {
        Course currentCourse = courseService.getAllcourse().get(courseService.getcourse(courseId));
        for (int i = 0; i < teachers.size(); i++) {
            for (int j = 0; j < teachers.get(i).getCourses().size(); j++) {
                if (teachers.get(i).getCourses().get(j).equals(currentCourse)) {
                    return teachers.get(i).getName();
                }
            }
        }
        return "there is no teacher assign to this course";
    }

    public Integer getTeacher(String id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                return i;
            }

        }
        return null;
    }
}
