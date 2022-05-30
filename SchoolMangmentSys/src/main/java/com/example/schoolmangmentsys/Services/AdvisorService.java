package com.example.schoolmangmentsys.Services;


import com.example.schoolmangmentsys.model.Advisor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {

    ArrayList<Advisor> advisors = new ArrayList<>();
    public ArrayList<Advisor>getAdvi() {
        return advisors;


    }

    public Boolean addAdvi(Advisor advisor) {
        return advisors.add(advisor);
    }

    public ArrayList<Advisor> delAdvi(Integer index) {
        advisors.remove(index);
        return advisors;
    }

    public ArrayList<Advisor>updAdvi(ArrayList<String> advisorsNames, Integer index) {
        advisorsNames.set(index ,"");
        return advisors;
    }
}
