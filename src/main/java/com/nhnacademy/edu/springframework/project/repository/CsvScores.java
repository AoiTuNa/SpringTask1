package com.nhnacademy.edu.springframework.project.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
public class CsvScores implements Scores {
    private final String SCORE_PATH = "data/score.csv";

    private static final List<Score> scores =new ArrayList<>();
    private CsvScores(){}


    public static Scores getInstance() {

        return LazyHolder.instance;
    }

    @Override
    public void load() {
        scores.clear();
        String line ="";
        try(    InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(SCORE_PATH)));
                BufferedReader br = new BufferedReader(reader)) {
            while ((line = br.readLine()) != null){
                String[] lineArr = line.split(",");
                Score score = new Score(Integer.parseInt(lineArr[0]),Integer.parseInt(lineArr[1]));
                scores.add(score);
            }
        }catch (FileNotFoundException e){
                log.warn("FileNotFoundException in {}", this.getClass());
        }catch (IOException e){
            log.warn("IOException in {}", this.getClass());
        }
    }

    @Override
    public List<Score> findAll() {
        return scores;
    }

    private static class LazyHolder{
        private static final Scores instance = new CsvScores();
    }
}
