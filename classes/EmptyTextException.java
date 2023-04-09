package com.example.gymhelperapp;

public class EmptyTextException extends Exception{
    public EmptyTextException() {
        super("Please input the exercise name");
    }
}
