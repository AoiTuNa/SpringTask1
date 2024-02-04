package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("defaultGradeQueryService")
public class DefaultGradeQueryService implements GradeQueryService {

    private final Students students;
    private final Scores scores;

    @Autowired
    public DefaultGradeQueryService(Students students,Scores scores){
        this.students = students;
        this.scores = scores;
    }


    @Override
    public List<Score> getScoreByStudentName(String name) {
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
        List<Score> scoreList = scores.findAll();
        for(Score score : scoreList){
            if(score.getStudentSeq() == seq){
                return score;
            }
        }
        throw new NoSuchElementException();
    }
}
