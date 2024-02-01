package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service("csvStudent")
public class CsvStudents implements Students {

    private static final List<Student> students = new ArrayList<>();
    private CsvStudents(){}

    /** DO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        return LazyHolder.instance;
    }

    // DO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        File csv = new File("src/main/resources/data/student.csv");
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader(csv))){
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

    /**
     * DO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for(Score score : scores){
            for(Student student : students){
                if(score.getStudentSeq()==student.getSeq()){
                    student.setScore(score);
                    break;
                }
            }

        }
    }
    private static class LazyHolder{
        private static final Students instance = new CsvStudents();
    }
}
