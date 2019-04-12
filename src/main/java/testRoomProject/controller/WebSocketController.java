package testRoomProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import testRoomProject.entity.Room;
import testRoomProject.repository.RoomsRepo;

@Controller
public class WebSocketController {

    @Autowired
    RoomsRepo roomsRepo;

    @MessageMapping("/update")
    @SendTo("/general/publicRoom")
    public Room sendMessage(@Payload Room room) {

        if(room.getStatus() == Room.BulbStatus.OFF)
            room.setStatus(Room.BulbStatus.ON);
        else room.setStatus(Room.BulbStatus.OFF);

        roomsRepo.updateRoomStatus(room.getId(), room.getStatus());

        return room;
    }
}