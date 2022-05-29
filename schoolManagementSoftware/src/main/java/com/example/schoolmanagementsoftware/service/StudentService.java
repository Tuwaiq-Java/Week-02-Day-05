package com.example.schoolmanagementsoftware.service;


import com.example.schoolmanagementsoftware.model.Course;
import com.example.schoolmanagementsoftware.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final CourseService courseService;
    ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Student> getAllStudent(){
        return this.students;
    }

    public boolean addStudent(Student student){
        return students.add(student);
    }

    public boolean updateStudent(Student student){
        if(students.set(getStudent(student.getId()),student)!=null){
        return true;
        }
        return false;
    }

    public boolean deleteStudent(String id){
        return students.remove(getStudent(id));
    }

    public boolean addCourseToStudent(String studentId,String courseId){
     Student currentStudent=students.get(getStudent(studentId));
     Course currentCourse=courseService.getAllcourse().get(courseService.getcourse(courseId));
     if (currentStudent!=null || currentCourse!=null){
         currentStudent.getCourses().add(currentCourse);
         return true;
     }
     return false;
    }

    public boolean changeMajor(String studentId ,String major){
        Student currentStudent=students.get(getStudent(studentId));
        if(currentStudent!=null){
           currentStudent.setMajor(major);
           currentStudent.getCourses().clear();
           return true;
        }
        return false;
    }
    public ArrayList<Student> getStudentsByCourse(String courseId){
        ArrayList<Student>courseStudent=new ArrayList<>();
        Course currentCourse=courseService.getAllcourse().get(courseService.getcourse(courseId));
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < students.get(i).getCourses().size(); j++) {
                if (students.get(i).getCourses().get(j).equals(currentCourse)){
                    courseStudent.add(students.get(i));
                }
            }
        }

        return courseStudent;
    }


    public Integer getStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return i;
            }

        }
        return null;
    }


}
