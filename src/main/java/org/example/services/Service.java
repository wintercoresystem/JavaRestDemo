package org.example.services;


public class Service {
    public static final String INCORRECT_ID_MESSAGE = "Incorrect Id";

    public boolean verifyLong(String id) {
        try {
            Long.parseLong(id);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }


}
