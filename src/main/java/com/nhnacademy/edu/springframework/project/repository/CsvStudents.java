package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service("csvStudent")
public class CsvStudents implements Students {

    private static final List<Student> students = new ArrayList<>();
    private final String STUDENT_PATH = "data/student.csv";
    public CsvStudents(){}


    @Override
    public void load() {
        String line = "";
        students.clear();
        try(InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(STUDENT_PATH)));
            BufferedReader br = new BufferedReader(reader)){
            while ((line = br.readLine()) != null){
                String[] lineArr = line.split(",");
                Student student = new Student((Integer.parseInt(lineArr[0])),lineArr[1]);
                students.add(student);
            }
        }catch (FileNotFoundException e){
            log.warn("FileNotFoundException in {}", this.getClass());
        }catch (IOException e){
            log.warn("IOException in {}", this.getClass());
        }
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public void merge(Collection<Score> scores) {
        for(Student student : students){
            student.setScore(new Score(student.getSeq(),0));
        }

        for(Score score : scores){
            for(Student student : students){
                if(score.getStudentSeq()==student.getSeq()){
                    student.setScore(score);
                    break;
                }
            }

        }
    }
}
