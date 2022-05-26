package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Advisor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {
    private ArrayList<Advisor> advisors = new ArrayList<>();
    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public Boolean addAdvisor(Advisor advisor) {
        if(isAdvisorExists(advisor.getID()))
            return false;
        return advisors.add(advisor);
    }

    public Boolean editAdvisor(Advisor advisor) {
        for (int i = 0; i < advisors.size(); i++) {
                if (advisors.get(i).getID().equals(advisor.getID())) {
                    advisors.set(i,advisor);
                    return true;
                }
            }
        return false;
    }

    public Boolean isAdvisorExists(String id) {
        for (int i = 0; i < advisors.size(); i++) {
            if (advisors.get(i).getID().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public Advisor returnIsAdvisorExists(String id) {
        for (int i = 0; i < advisors.size(); i++) {
            if (advisors.get(i).getID().equals(id)) {
                return advisors.get(i);
            }
        }
        return null;
    }

    public Boolean deleteAdvisor(String advisorID) {
        for (int i = 0; i <advisors.size(); i++) {
            if (advisors.get(i).getID().equals(advisorID)) {
                advisors.remove(i);
                return true;
            }
        }
        return false;
    }
}
