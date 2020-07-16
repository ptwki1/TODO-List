package com.tms.todolist.service.impl;

import com.tms.todolist.dto.UserDto;
import com.tms.todolist.dto.UserDtoReq;
import com.tms.todolist.model.Role;
import com.tms.todolist.model.Task;
import com.tms.todolist.model.User;
import com.tms.todolist.repository.RoleRepository;
import com.tms.todolist.repository.TaskRepository;
import com.tms.todolist.repository.UserRepository;
import com.tms.todolist.service.UserService;
import com.tms.todolist.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserDto addUser(UserDtoReq userDtoReq) {
        List<Task> tasks=taskRepository.findAllById(userDtoReq.getTasks());
        List<Role> roles=roleRepository.findAllById(userDtoReq.getRoles());
        User user=userRepository.saveAndFlush(userMapper.fromUserDtoReq(userDtoReq,tasks,roles));
        return userMapper.toUserDto(user);
    }

    @Override
    public void delete(Long id) {
       taskRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        User user=userRepository.findUserById(id);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto editTask(Long id, UserDtoReq userDtoReq) {
        if(userRepository.findById(id).isPresent()){
            User user=userRepository.findUserById(id);

            user.setPassword(userDtoReq.getPassword());
            user.setName(userDtoReq.getName());
            user.setLogin(userDtoReq.getLogin());
            user.setAvatar(userDtoReq.getAvatar());
            user.setTasks(taskRepository.findAllById(userDtoReq.getTasks()));
            user.setUpdated(userDtoReq.getUpdated());
            user.setActive(userDtoReq.isActive());
            return userMapper.toUserDto(user);
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserDto> findByName(String name) {
        List<User> users=userRepository.findByPartName(name);
        List<UserDto> userDtos=new ArrayList<>();
        for(User user:users){
            userDtos.add(userMapper.toUserDto(user));
        }
        return userDtos;
    }
}
