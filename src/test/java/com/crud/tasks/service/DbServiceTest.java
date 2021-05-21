package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository repository;

    @Test
    void testGetAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test task", "Test content"));
        tasks.add(new Task(2L, "Test task", "Test content"));
        tasks.add(new Task(3L, "Test task", "Test content"));

        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> tasksFromService = service.getAllTasks();

        //Than
        assertEquals(tasks, tasksFromService);
    }

    @Test
    void testGetTask() {
        //Given
        Task task = new Task(1L, "Test task", "Test content");

        when(repository.findById(task.getId())).thenReturn(Optional.of(task));

        //When
        Optional<Task> taskFromService = service.getTask(1L);

        //Then
        assertEquals(task,taskFromService.get());
    }

    @Test
    void testSaveTask() {

        //Given
        Task task = new Task(1L, "Test task", "Test content");
        when(repository.save(task)).thenReturn(task);

        //When

        Task taskSaved = service.saveTask(task);

        //Then
        assertEquals(1L, taskSaved.getId());
        assertEquals("Test task", taskSaved.getTitle());
        assertEquals("Test content", taskSaved.getContent());

    }

    @Test
    void testDeleteTask() {

        //Given
        Task task = new Task(1L, "Test", "Test content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task taskSaved = service.saveTask(task);
        service.deleteTask(taskSaved.getId());

        //Than
        verify(repository, times(1)).deleteById(1L);

    }
}