package com.chat.backend.repositories;

import com.chat.backend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room,String> {

    // get room using roomId
    Room findByRoomId(String roomId);
}
