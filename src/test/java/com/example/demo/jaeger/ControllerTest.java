package com.example.demo.jaeger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.demo.jaeger.controller.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testPath1() throws Exception {
        when(restTemplate.getForObject("http://localhost:8090/hello/path2", String.class)).thenReturn("mock response");

        mockMvc.perform(MockMvcRequestBuilders.get("/hello/path1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("response from /path1 + mock response"));
    }

    @Test
    public void testPath2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/path2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("response from /path2 "));
    }
}
