package com.tms.todolist.service.impl;

import com.tms.todolist.dto.RoomDto;
import com.tms.todolist.dto.RoomDtoReq;
import com.tms.todolist.model.Task;
import com.tms.todolist.model.room.Room;
import com.tms.todolist.model.User;
import com.tms.todolist.model.room.TaskRoom;
import com.tms.todolist.model.room.UserRoom;
import com.tms.todolist.repository.RoomRepository;
import com.tms.todolist.repository.TaskRepository;
import com.tms.todolist.repository.UserRepository;
import com.tms.todolist.repository.UserRoomRepository;
import com.tms.todolist.service.RoomService;
import com.tms.todolist.service.mapper.RoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.tms.todolist.model.room.UserRoom.RequestStatus.PENDING;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserRoomRepository userRoomRepository;
    private final TaskRepository taskRepository;
    @Override
    public RoomDto addRoom(final RoomDtoReq roomDtoReq) {
        List<User> users = userRepository.findAllById(roomDtoReq.getUsers());
        List<Task> tasks=taskRepository.findAllById(roomDtoReq.getTasks());
        Room room = roomRepository.save(roomMapper.fromRoomDtoReq(roomDtoReq, users,tasks));

        return roomMapper.toRoomDto(room);
    }

    //TODO: why are Set is better than List for @OneToMany/@ManyToOne

    @Override
    public RoomDto editRoom(final Long id, final RoomDtoReq roomDtoReq) {
        return roomRepository.findById(id).map(room -> {
            final List<UserRoom> userRooms = userRepository.findAllById(roomDtoReq.getUsers()).stream()
                    .map(user -> mapUserRoom(room, user))
                    .collect(toList());
            final List<TaskRoom> taskRoomList = taskRepository.findAllById(roomDtoReq.getTasks()).stream()
                    .map(task -> mapTaskRoom(room,task))
                    .collect(toList());


            room.getUserRooms().clear();
            room.getUserRooms().addAll(userRooms);

            room.getTaskRoomList().clear();
            room.getTaskRoomList().addAll(taskRoomList);

            room.setName(roomDtoReq.getName());
            room.setActive(roomDtoReq.isActive());
            room.setUpdated(roomDtoReq.getUpdated());

            return roomMapper.toRoomDto(room);
        }).orElse(null); //
    }

    private UserRoom mapUserRoom(final Room room, final User user) {
        return Optional.ofNullable(room.getUserRooms()).orElse(emptyList()).stream()
                .filter(userRoom -> userRoom.getUser().equals(user))
                .findAny()
                .orElse(UserRoom.builder()
                        .room(room)
                        .user(user)
                        .requestStatus(PENDING)
                        .build());
    }

    private TaskRoom mapTaskRoom(final Room room, final Task task) {
        return Optional.ofNullable(task.getTaskRoomList()).orElse(emptyList()).stream()
                .filter(taskRoom -> taskRoom.getTask().equals(task))
                .findAny()
                .orElse(TaskRoom.builder()
                        .room(room)
                        .task(task)
                        .build());
    }
}
