package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDtoToMap = new TaskDto(null, "Test", "Test");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDtoToMap);

        //Then
        Assert.assertEquals("Test", mappedTask.getTitle());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task taskToMap = new Task(null, "Test", "Test");

        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(taskToMap);

        //Then
        Assert.assertEquals("Test", mappedTaskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(null, "Test", "Test");
        tasks.add(task);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        Assert.assertEquals(1, taskDtos.size());
    }
}