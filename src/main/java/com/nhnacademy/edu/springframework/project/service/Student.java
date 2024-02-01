package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Student {
    private final int seq;
    private final String name;
    @Setter

    private Score score;

    public Student(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student Student1 = (Student) o;
        return seq == Student1.seq &&
                Objects.equals(name, Student1.name)&&
                score == Student1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, name, score);
    }

    @Override
    public String toString() {
        return "Student{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}'+ '\n';
    }
}
