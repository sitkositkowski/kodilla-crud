package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void mapToTask() {
        //Given
        TaskDto taskDto =  new TaskDto(1L,"Test task","Test content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId());
        assertEquals("Test task", task.getTitle());
        assertEquals("Test content", task.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task =  new Task(1L,"Test task","Test content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("Test task", taskDto.getTitle());
        assertEquals("Test content", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"Test task","Test content"));
        //When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(1, tasksDto.size());
        assertEquals(1L, tasksDto.get(0).getId());
        assertEquals("Test task", tasksDto.get(0).getTitle());
        assertEquals("Test content", tasksDto.get(0).getContent());
    }
}