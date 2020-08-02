package com.tms.todolist.service.impl;

import com.tms.todolist.dto.TaskDto;
import com.tms.todolist.dto.TaskDtoReq;
import com.tms.todolist.dto.TaskShortDto;
import com.tms.todolist.model.Task;
import com.tms.todolist.model.TaskStatus;
import com.tms.todolist.model.User;
import com.tms.todolist.repository.TaskRepository;
import com.tms.todolist.repository.UserRepository;
import com.tms.todolist.service.TaskService;
import com.tms.todolist.service.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto addTask(final TaskDtoReq taskDtoReq) {
        List<User> users = userRepository.findAllById(taskDtoReq.getUserIds());
        Task task = taskRepository.save(taskMapper.fromTaskDtoReq(taskDtoReq, users));

        users.forEach(user -> Optional.ofNullable(user.getTasks()).ifPresent(tasks -> tasks.add(task)));

        return taskMapper.toTaskDto(task);
    }

    @Override
    public void delete(final Long id) {
        taskRepository.findById(id)
                .ifPresent(task -> {
                    task.getUsers().forEach(user -> user.getTasks().remove(task));
                    taskRepository.delete(task);
                });
    }

    @Override
    public TaskDto findById(final Long userId, final Long taskId) {
//        final Task task = userRepository.findById(userId)
//                .map(user -> Optional.ofNullable(user.getTasks())
//                        .flatMap(tasks -> tasks.stream()
//                                .filter(t -> taskId.equals(t.getId()))
//                                .findAny())
//                        .orElseThrow(() -> new IllegalArgumentException("There are no Task with given id")))
//                .orElseThrow(() -> new IllegalArgumentException("There are no User with given id"));

        //////

//        final User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("There are no User with given id"));
//
//        if (user.getTasks() == null) {
//            throw new IllegalArgumentException("There are no Task with given id");
//        }
//
//        final Task task = user.getTasks().stream()
//                .filter(t -> taskId.equals(t.getId()))
//                .findAny()
//                .orElseThrow(() -> new IllegalArgumentException("There are no User with given id"));

        final Task task = taskRepository.findByTaskIdAndUser(taskId, userId)
                .orElseThrow(() -> new IllegalArgumentException("There are no User with given id"));

        return taskMapper.toTaskDto(task);
    }

    @Override
    public TaskDto findById(final Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toTaskDto)
                .orElseThrow(() -> new IllegalArgumentException("There are no Task with given id"));
    }

    @Override
    public TaskDto editTask(Long id, TaskDtoReq taskDtoReq) {
        if (taskRepository.findById(id).isPresent()) {
            Task task = taskRepository.findTaskById(id);

            task.setStatus(taskDtoReq.getStatus());
            task.setActive(taskDtoReq.isActive());
            task.setDescription(taskDtoReq.getDescription());
            task.setUsers(userRepository.findAllById(taskDtoReq.getUserIds()));
            task.setUpdated(taskDtoReq.getUpdated());
            return taskMapper.toTaskDto(task);
        } else {
            return null;
        }
    }

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskShortDto> allActive() {
        return taskRepository.findTasksByActive(true).stream()
                .map(taskMapper::toTaskShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto doneTask(Long id) {
        if (taskRepository.findById(id).isPresent()) {
            Task task = taskRepository.findTaskById(id);

            task.setActive(false);
            task.setStatus(TaskStatus.FINISH);

            return taskMapper.toTaskDto(task);
        } else {
            return null;
        }
    }

    @Override
    public TaskDto newStatus(Long id, TaskDtoReq taskDtoReq) {
        if (taskRepository.findById(id).isPresent()) {
            Task task = taskRepository.findTaskById(id);

            task.setStatus(taskDtoReq.getStatus());

            return taskMapper.toTaskDto(task);
        } else {
            return null;
        }
    }
}
