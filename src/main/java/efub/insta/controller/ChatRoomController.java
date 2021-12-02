package efub.insta.controller;


import efub.insta.dto.ChatMessageDto;
import efub.insta.dto.ChatRoomResponseDto;
import efub.insta.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/chat/senders/{userId}")
    public List<ChatRoomResponseDto> findByChatRoomBySender(@PathVariable String userId){
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.findAllRoomsBySender(userId);
        return chatRoomResponseDtos;
    }

    @GetMapping("chat/roomList")
    public List<ChatRoomResponseDto> findAllRooms() {
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.findAllRooms();
        return chatRoomResponseDtos;
    }

    @GetMapping("/list/{roomNo}")
    public List<ChatMessageDto> getChatList(@PathVariable String roomNo){
        return chatRoomService.getMsgList(roomNo);
    }
}