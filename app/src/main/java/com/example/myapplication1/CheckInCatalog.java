
package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.Event;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.BitSet;

    public class CheckInCatalog extends AppCompatActivity {

        private static CheckInCatalog singleCatalog;
        private ArrayList<Event> checkInCatalog = new ArrayList<Event>();
        public CheckInCatalog(){
        }

        public static CheckInCatalog getSingleCatalog(){
            if(singleCatalog == null){
                singleCatalog = new CheckInCatalog();
            }
            return singleCatalog;
        }
        public  ArrayList<Event> getCatalog(){
            return checkInCatalog;
        }

        public void addEvent(){
           for(int i =0;i<EventCatalog.getSingleCatalog().getNumberOfEvent();i++) {
                 if(EventCatalog.getSingleCatalog().getCatalog().get(i).getClock_in()==true) {
                    checkInCatalog.add(EventCatalog.getSingleCatalog().getCatalog().get(i));
                 }
           }
        }

        public int getNumberOfEvent(){
            return checkInCatalog.size();
        }

        public void removeEvent(Event e){
            checkInCatalog.remove(e);
        }

        public void removeEventAll(){
            while(checkInCatalog.size()>0){
                checkInCatalog.remove(0);
            }
        }
    }

