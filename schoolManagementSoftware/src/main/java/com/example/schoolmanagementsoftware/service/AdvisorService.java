package com.example.schoolmanagementsoftware.service;


import com.example.schoolmanagementsoftware.model.Advisor;

import com.example.schoolmanagementsoftware.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AdvisorService {

    private final StudentService studentService;
    ArrayList<Advisor> advisors=new ArrayList<>();

    public ArrayList<Advisor> getAllAdvisors(){
        return this.advisors;
    }

    public boolean addAdvisor(Advisor advisor){
        return advisors.add(advisor);
    }

    public boolean updateAdvisor(Advisor advisor){
        if(advisors.set(getAdvisor(advisor.getId()),advisor)!=null){
            return true;
        }
        return false;
    }

    public boolean deleteAdvisor(String id){
        return advisors.remove(getAdvisor(id));
    }

    public ArrayList<Student> getStudents(String advisorId){
        System.out.println("inside method");
        ArrayList<Student> students=new ArrayList<>();
        Advisor advisor = advisors.get(getAdvisor(advisorId));
        for (int i = 0; i < studentService.getAllStudent().size(); i++) {
            if(advisor.getName().equalsIgnoreCase(studentService.getAllStudent().get(i).getAdvisorName())){
                students.add(studentService.getAllStudent().get(i));
            }
        }

        return students;
    }

    public Integer getAdvisor(String id) {
        for (int i = 0; i < advisors.size(); i++) {
            if (advisors.get(i).getId().equals(id)) {
                return i;
            }

        }
        return null;
    }
}
