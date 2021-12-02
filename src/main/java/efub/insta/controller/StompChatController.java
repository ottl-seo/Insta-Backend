package efub.insta.controller;

import efub.insta.dto.ChatMsgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template; // 특정 Broker로 메시지 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMsgDto message){
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("sub/chat/room" + message.getRoomNo(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMsgDto message){
        template.convertAndSend("/sub/chat/room/"+message.getRoomNo(), message);
    }

}
