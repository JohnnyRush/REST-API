package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuit {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    TaskController taskController;
    @MockBean
    TaskMapper taskMapper;
    @MockBean
    DbService dbService;

    @Test
    public void shouldFetchEmptyTaskDtoList() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        Mockito.when(taskController.getTasks()).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))//or is OK)
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldFetchTaskDtoList() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");
        taskDtoList.add(taskDto);
        Mockito.when(taskController.getTasks()).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Test")));
    }

    @Test
    public void shouldFetchTaskDto() throws Exception, TaskNotFoundException {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");
        Mockito.when(taskController.getTask(ArgumentMatchers.anyLong())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTask").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = (new TaskDto(1L, "Test", "Test"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When and Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/task/deleteTask").param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");
        TaskDto updatedTaskDto = new TaskDto(1L, "updateTest", "updateTest");

        Mockito.when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When &  Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("updateTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("updateTest")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}