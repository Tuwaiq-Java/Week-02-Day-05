package com.example.schoolmanagementsoftware.service;

import com.example.schoolmanagementsoftware.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    
    ArrayList<Course>courses= new ArrayList<>();

    public ArrayList<Course> getAllcourse(){
        return this.courses;
    }

    public boolean addCourse(Course course){
        return courses.add(course);
    }

    public boolean updateCourse(Course course){
        if(courses.set(getcourse(course.getId()),course)!=null){
            return true;
        }
        return false;
    }

    public boolean deleteCourse(String id){
        return courses.remove(getcourse(id));
    }

    public Integer getcourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return i;
            }

        }
        return null;
    }
}
