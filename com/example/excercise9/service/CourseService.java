package com.example.excercise9.service;

import com.example.excercise9.model.Course;
import com.example.excercise9.model.Student;
import com.example.excercise9.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor

public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();

    private final StudentService studentService;

    private final TeacherService teacherService;

    public ArrayList<Course> getCourses() {

        return courses;
    }

    public boolean isAdd(Course course){
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).equals(course)){
                return false;
            }
        }
        courses.add(course);
        return true;
    }

    public boolean isUpdate(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if ((courses.get(i).getId().equals(course.getId()))) {

                courses.set(i,course);
                return true;
            }
        }
        return false;
    }

    public boolean isDelete(Course course) {

        return courses.remove(course);
    }

    public Course getCourse(String courseId) {
        for (Course course : courses) {
            if(course.getId().equals(courseId)){
                return course;
            }
        }
        return null;
    }

    public Integer addStudentToList(String studentId, String courseId){

        Student currentStudent = studentService.getStudent(studentId);
        Course currentCourse = getCourse(courseId);

        if(currentStudent == null){
            return -1;
        }
        if(currentCourse == null){
            return 0;
        }

        currentStudent.getCoursesList().add(currentCourse);
        return 1;
    }

    public Integer addTeacherToList(String teacherId, String courseId){

        Teacher currentTeacher = teacherService.getTeacher(teacherId);
        Course currentCourse = getCourse(courseId);

        if(currentTeacher == null){
            return -1;
        }
        if(currentCourse == null){
            return 0;
        }

        currentTeacher.getCourseList().add(currentCourse);
        return 1;
    }

    public Integer getNameTeacher(String coursedId){
        Course currentCourse = getCourse(coursedId);
        Teacher currentTeacher = teacherService.getTeacher(currentCourse.getName());

        if(currentCourse == null){
            return 0;
        }
        currentTeacher.getName();
        return 1;
    }


}
