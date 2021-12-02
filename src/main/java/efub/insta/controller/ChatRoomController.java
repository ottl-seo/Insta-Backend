package efub.insta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import efub.insta.domain.ChatRoom;
import efub.insta.domain.ChatRoomRepository;
import efub.insta.domain.User;
import efub.insta.domain.UserRepository;
import efub.insta.dto.ChatRoomDto;
import efub.insta.service.ChatRoomService;
import efub.insta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms/senders")
    @ResponseBody
    public List<ChatRoomDto> room(@RequestParam String senderId) {
        User user = userRepository.findUserByUserId(senderId);
        return chatRoomService.findAllRoomsBySender(user);
        //return chatRoomRepository.findAllRoom();
    }

    @GetMapping("/rooms")
    public List<ChatRoomDto> room(){
        return chatRoomService.findAllRooms();
    }


}