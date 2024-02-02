package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.configuration.MainConfig;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {MainConfig.class}
)
class ScoresTest {

    @Autowired
    private Scores scores; // 스프링 컨텍스트로부터 Scores 빈 주입 받기

    @Test
    void load(){
        scores.load(); // 주입 받은 빈 사용
        assertNotNull(scores.findAll());
    }

    @Test
    void findAll() {
        Score testTrueScore = new Score(2,80);
        Score testFalseScore = new Score(1,60);
        scores.load(); // 주입 받은 빈 사용
        assertTrue(scores.findAll().contains(testTrueScore));
        assertFalse(scores.findAll().contains(testFalseScore));
    }
}
