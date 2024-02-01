package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GradeQueryService {
    List<Score> getScoreByStudentName(String name);
    Score getScoreByStudentSeq(int seq);
}
