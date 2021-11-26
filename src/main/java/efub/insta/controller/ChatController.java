package efub.insta.controller;

import efub.insta.dto.ChatRoomDto;
import efub.insta.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    //@Autowired
    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }
    @GetMapping
    public List<ChatRoomDto> findAllRoom(){
        return chatService.findAllRoom();
    }
}
