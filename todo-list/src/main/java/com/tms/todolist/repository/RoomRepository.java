package com.tms.todolist.repository;

import com.tms.todolist.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select s from Room s where lower(s.name) like lower(concat('%', :name,'%'))")
    List<Room> findByPartName(String name);

    Room findRoomById(Long id);
}
