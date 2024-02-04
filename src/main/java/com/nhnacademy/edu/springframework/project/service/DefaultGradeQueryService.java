package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DefaultGradeQueryService implements GradeQueryService {
    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students students = CsvStudents.getInstance();
        List<Student> studentList = (List<Student>) students.findAll();
        List<Score> studentScores = new ArrayList<>();
        for(Student student : studentList){
            if(student.getName().equals(name)){
                studentScores.add(student.getScore());
            }
        }
        return studentScores;

    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Scores scores = CsvScores.getInstance();
        List<Score> scoreList = scores.findAll();
        for(Score score : scoreList){
            if(score.getStudentSeq() == seq){
                return score;
            }
        }
        throw new NoSuchElementException();
    }
}
