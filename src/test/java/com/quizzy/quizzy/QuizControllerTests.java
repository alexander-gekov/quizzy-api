package com.quizzy.quizzy;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.repository.QuizRepository;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(QuizResource.class)
public class QuizControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuizRepository quizRepositoryMock;

    //ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getQuizzes() throws Exception {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());
        List<Quiz> quizList = quizRepositoryMock.findAll();
        quizList.add(quiz);
//        Quiz savedQuiz = quizRepositoryMock.save(quiz);
        Mockito.when(quizRepositoryMock.findAll()).thenReturn(quizList);
//        BDDMockito.given(quizRepositoryMock.findAll()).willReturn(quizList);
//        mockMvc.perform(get("/quizzes").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("[\n" +
//                        "    {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"name\": \"name\",\n" +
//                        "        \"topic\": \"topic\",\n" +
//                        "        \"number_of_questions\": 0,\n" +
//                        "        \"questions\": []\n" +
//                        "    }\n" +
//                        "]"));
        this.mockMvc.perform(get("/quizzes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{" +
                        "\"id\":0," +
                        "\"name\":\"name\"," +
                        "\"topic\":\"topic\"," +
                        "\"number_of_questions\":0," +
                        "\"questions\":[]" +
                        "}]"));
    }
}