package com.example.school.Services;

import com.example.school.Models.Advisor;
import com.example.school.Models.Classes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Advisorservice {
    private ArrayList<Advisor> advisors;



    public ArrayList<Advisor> getClasses(){
        return advisors;
    }
    public boolean isAdd(Advisor advisor) {
        return advisors.add(advisor);
    }

    public boolean isUpdate(Advisor advisor) {
        for (int i = 0; i < advisors.size() ; i++) {
            if (advisors.get(i).getAdvisortId().equals(advisor.getAdvisortId()))
                advisors.add(i,advisor);
            return true;
        }
        return false;
    }


    public boolean isDelete(Advisor advisor) {
        return advisors.remove(advisor);


    }



}
