package com.example.dayex.service;

import com.example.dayex.model.Advisor;
import com.example.dayex.model.Classes;
import com.example.dayex.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AdvisorService {
    private final StudentService studentService;
    public boolean updateAdvisor(Advisor advisor){
        for (int i = 0; i < advisors.size(); i++) {
            if (advisors.get(i).getId().equals(advisor.getId()))
                advisors.set(i,advisor);
            return true;
        }
        return false;
    }
    public boolean deleteAdvisor(String id){
        Advisor advisor = getAdvisorById(id);
        if(advisor == null){
            return false;
        }
        advisors.remove(advisor);
        return true;
    }
    ArrayList<Advisor> advisors = new ArrayList<>();

    public ArrayList<Advisor> getAdvisors(){
        return advisors;
    }
    public boolean addAdvisor(Advisor advisor){
       return advisors.add(advisor);
    }

    public Advisor getAdvisorById(String id){
        for (Advisor advisor: advisors) {
            if(advisor.getId().equals(id))
                return advisor;
        }

        return null;
    }
    public ArrayList<Student> getStudentsAdvisor(String advisorID){
        ArrayList<Student> students1 = new ArrayList<>();//empty list
        ArrayList<Student> students =  studentService.getStudents();//students list
        Advisor ad = getAdvisorById(advisorID);
            for(int j = 0; j < students.size();j++){//loop through the studentList
                Student s1 = students.get(j);
                if(s1.getAdvisorName().equals(ad.getName())){//if the student advisor name matched the advisor

                    students1.add(s1);//add the student to the list
                }
            }


        return students1;//return the list
    }
}
