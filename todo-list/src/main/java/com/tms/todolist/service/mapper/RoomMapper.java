package com.tms.todolist.service.mapper;

import com.tms.todolist.dto.RoomDto;
import com.tms.todolist.dto.RoomDtoReq;
import com.tms.todolist.dto.RoomShortDto;
import com.tms.todolist.dto.UserShortDto;
import com.tms.todolist.model.room.Room;
import com.tms.todolist.model.User;
import com.tms.todolist.model.room.UserRoom;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tms.todolist.model.room.UserRoom.RequestStatus.PENDING;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Component
public class RoomMapper {

    public RoomDto toRoomDto(final Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .active(room.isActive())
                .created(room.getCreated())
                .updated(room.getUpdated())
                .name(room.getName())
                .users(Optional.ofNullable(room.getUserRooms())
                        .orElse(new ArrayList<>()).stream()
                        .map(UserRoom::getUser)
                        .map(this::toUserShortDto)
                        .collect(toList()))
                .build();
    }

    private UserShortDto toUserShortDto(final User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public RoomShortDto toRoomShortDto(final Room room) {
        return RoomShortDto.builder()
                .name(room.getName())
                .build();
    }

    public Room fromRoomDtoReq(final RoomDtoReq roomDtoReq, final List<User> users) {
        final Room room = Room.builder()
                .active(roomDtoReq.isActive())
                .created(roomDtoReq.getCreated())
                .updated(roomDtoReq.getUpdated())
                .name(roomDtoReq.getName())
                .userRooms(Optional.ofNullable(users).orElse(emptyList())
                        .stream()
                        .map(user -> UserRoom.builder()
                                .user(user)
                                .requestStatus(PENDING)
                                .build())
                        .collect(toList()))
                .build();

        room.getUserRooms().forEach(userRoom -> userRoom.setRoom(room));

        return room;
    }

}
