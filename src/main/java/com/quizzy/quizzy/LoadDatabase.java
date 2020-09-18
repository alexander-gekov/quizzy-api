package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Answer;
import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import com.quizzy.quizzy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initUsers(UserRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new User("Bilbo Baggins", "burglar@gmail.com","123")));
            log.info("Preloading " + repository.save(new User("Frodo Baggins", "thief@gmail.com","123")));
            log.info("Preloading " + repository.save(new User("Alexander Gekov", "example1@gmail.com","123")));
            log.info("Preloading " + repository.save(new User("Viktor Naydenov", "example2@gmail.com","123")));
            log.info("Preloading " + repository.save(new User("Aylin Osmanova", "example3@gmail.com","123")));
            log.info("Preloading " + repository.save(new User("Yulia Krusharska", "example4@gmail.com","1234")));
        };
    }

    @Bean
    CommandLineRunner initQuizzes(QuizRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Quiz("Quiz 1", "Geography")));
            log.info("Preloading " + repository.save(new Quiz("Quiz 2", "Biography")));
            log.info("Preloading " + repository.save(new Quiz("Quiz 3", "Top 10 Youtubers")));
            log.info("Preloading " + repository.save(new Quiz("Quiz 4", "Who knows Aleksandar the best?")));
        };
    }

}
