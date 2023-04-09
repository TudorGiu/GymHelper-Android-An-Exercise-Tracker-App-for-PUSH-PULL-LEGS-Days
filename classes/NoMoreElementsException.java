package com.example.gymhelperapp;

public class NoMoreElementsException extends Exception {

    public NoMoreElementsException() {
        super("There are no exercises to delete");
    }
}
