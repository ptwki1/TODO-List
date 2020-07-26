package com.tms.todolist.rest;

import com.tms.todolist.dto.RoomDto;
import com.tms.todolist.dto.RoomDtoReq;
import com.tms.todolist.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public RoomDto addRoom(@RequestBody @Validated RoomDtoReq roomDtoReq) {
        return roomService.addRoom(roomDtoReq);
    }

    @PutMapping("/rooms/{id}")
    public RoomDto editRoom(@PathVariable Long id, @RequestBody RoomDtoReq roomDtoReq) {
        return roomService.editRoom(id, roomDtoReq);
    }

}
