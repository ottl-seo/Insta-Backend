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
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/senders/{userId}")
    public List<ChatRoomResponseDto> findByChatRoomBySender(@PathVariable String userId){
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.findAllRoomsBySender(userId);
        return chatRoomResponseDtos;
    }

    @GetMapping("/roomList")
    public List<ChatRoomResponseDto> findAllRooms() {
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.findAllRooms();
        return chatRoomResponseDtos;
    }

    @GetMapping("chat/list/{roomNo}")
    public List<ChatMessageDto> getChatList(@PathVariable String roomNo){
        return chatRoomService.getMsgList(roomNo);
    }

    @GetMapping("/chatInfo")
    public List<String[]> getLastChatting(){
        return chatRoomService.getLastMsgList();
    }
}