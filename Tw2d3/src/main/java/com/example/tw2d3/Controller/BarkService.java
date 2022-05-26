package com.example.tw2d3.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BarkService {
    ArrayList<Bark> rides = new ArrayList<>();
    ArrayList<AddTicket> ticket = new ArrayList<>();

    public ArrayList getRides() {
        return rides;
    }


    public void add(Bark bark) {
        rides.add(bark);
        String id1 = bark.getRideID();
        int tnum = bark.getTickets();
        AddTicket t1 = new AddTicket(id1, tnum);
        ticket.add(t1);
    }

    public boolean put(int index, Bark bark) {
        if (index < this.rides.size() && index >= 0) {
            this.rides.set(index, bark);
            return true;
        } else
            return false;
    }

    public void delete(int index) {
        rides.remove(index);
    }

    public Integer sale(String id, int amount) {
        for (int i = 0; i < this.rides.size(); i++) {
            Bark R1 = (Bark) this.rides.get(i);
            if (R1.getRideID().equals(id)) {
                if (R1.getTickets() > 0) {
                    if (R1.getPrice() > amount || R1.getPrice() < amount) {
                        return -1;
                    } else {
                        int number = R1.getTickets() - 1;
                        R1.setTickets(number);
                        rides.add(i, R1);
                        return 0;
                    }
                }
            }
        }
        return 1;

    }

    public boolean refile(String id) {
        for (int i = 0; i < this.rides.size(); i++) {
            Bark R2 = (Bark) this.rides.get(i);
            if (R2.getRideID().equals(id)) {
                if (R2.getTickets() == 0) {
                    for (int x = 0; x < this.ticket.size(); x++) {
                        AddTicket T1 = (AddTicket) this.ticket.get(x);
                        if (T1.getId().equals(id))
                            R2.setTickets(T1.getNum());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

