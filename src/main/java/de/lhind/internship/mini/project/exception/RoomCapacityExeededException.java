package de.lhind.internship.mini.project.exception;

public class RoomCapacityExeededException extends RuntimeException{
    public RoomCapacityExeededException(String message){
        super(message);
    }
}
