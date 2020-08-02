package com.tms.todolist.service;

import com.tms.todolist.dto.RoomDto;
import com.tms.todolist.dto.RoomDtoReq;

public interface RoomService {

    RoomDto addRoom(RoomDtoReq roomDtoReq);

    RoomDto editRoom(Long id, RoomDtoReq roomDtoReq);

//    RoomDto addTask(Long id,RoomDtoReq roomDtoReq);

}
