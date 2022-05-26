package com.example.springday05.service;

import com.example.springday05.model.Advisor;
import com.example.springday05.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {

    public ArrayList<Advisor> advisors = new ArrayList<>();

    public ArrayList<Advisor> getAdvisor() {
        return advisors;
    }

    public String addAdvisor(Advisor advisor) {
        advisors.add(advisor);
        return "Advisor add !";
    }

    public String editAdvisor(int index, Advisor advisor) {
        advisors.set(index,advisor);
        return "Advisor edit completed !";
    }

    public String deleteAdvisor(int index) {
        advisors.remove(index);
        return "Advisor is removed !";
    }

    public String getAdvisorName(String advisorID){
        for (int i = 0; i < advisors.size(); i++) {
            if(advisors.get(i).getId().equals(advisorID)){
                return advisors.get(i).getName();
            }
        }
        return null;
    }
}
