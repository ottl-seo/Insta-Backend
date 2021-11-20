package efub.insta.dto;

import efub.insta.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoomDto {
    private String roomNo;
    private String name;
    // DB연결 안 했으므로 임시로 해시셋에 세션(방 정보) 저장
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDto(String roomNo, String name){
        this.roomNo = roomNo;
        this.name = name;
    }
    public void handleActions(WebSocketSession session, ChatMsgDto chatMsgDto, ChatService chatService){
        if(chatMsgDto.getType().equals(ChatMsgDto.MsgType.ENTER)){
            sessions.add(session);
            chatMsgDto.setContent(chatMsgDto.getSender()+"님이 입장했습니다.");
        }
        sendMessage(chatMsgDto, chatService);
    }
    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
