package testRoomProject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testRoomProject.entity.Room;
import testRoomProject.repository.RoomsRepo;

@Controller
public class MainController {

    @Autowired
    private RoomsRepo roomsRepo;

    @RequestMapping("/")
    public String main(Model model) {

        Iterable<Room> rooms = roomsRepo.findAll();
        model.addAttribute("rooms", rooms);

        return "main";
    }

    @RequestMapping(value="/{id}")
    public String getRoom(@PathVariable("id") long id, Model model){

        Room room = roomsRepo.findById(id).orElse(new Room());
        model.addAttribute("room", room);

        return "room";

    }

    @PostMapping("/")
    public String add(Model model){

        Room room = new Room(Room.BulbStatus.OFF);
        roomsRepo.save(room);

        Iterable<Room> rooms = roomsRepo.findAll();
        model.addAttribute("rooms", rooms);

        return "main";
    }

}