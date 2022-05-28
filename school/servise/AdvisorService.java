package com.example.school.servise;

import com.example.school.model.Advisor;
import com.example.school.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service @RequiredArgsConstructor
public class AdvisorService {
    public ArrayList<Advisor> advisors = new ArrayList<>();


    public ArrayList<Advisor> getAdvisor() {
        return advisors;
    }

    public boolean addAdvisor(Advisor advisor) {
        advisors.add(advisor);
        return true;
    }

    public boolean updateAdvisor(int index,Advisor advisor) {
        advisors.set(index,advisor);
        return true;
    }

    public boolean deleteAdvisor(String advisorID) {
        advisors.remove(advisorID);
        return true;
    }
    /////////////=================//////////
}
