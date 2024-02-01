package com.nhnacademy.edu.springframework.project.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service("csvScore")
public class CsvScores implements Scores {
    private final String SCORE_PATH = "src/main/resources/data/score.csv";

    private static final List<Score> scores =new ArrayList<>();
    private CsvScores(){}

    /** DO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    public static Scores getInstance() {

        return LazyHolder.instance;
    }

    // DO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        File scoreCsv = new File(SCORE_PATH);
        String line ="";
        try(BufferedReader br = new BufferedReader(new FileReader(scoreCsv))) {
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
