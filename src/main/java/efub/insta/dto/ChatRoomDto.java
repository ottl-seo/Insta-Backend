package efub.insta.dto;

import efub.insta.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoomDto {
    private String roomNo;
    private String roomName;
    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    public static ChatRoomDto create(String name){
//        ChatRoomDto room = new ChatRoomDto();
//        room.roomNo= UUID.randomUUID().toString();
//        room.roomName=name;
//        return room;
//    }
//
    @Builder
    public ChatRoomDto(ChatRoom chatRoom){
        this.roomNo = chatRoom.getRoomNo();
        this.roomName = chatRoom.getRoomName();
    }
//    private String roomNo;
//    private String name;
//    // DB연결 안 했으므로 임시로 해시셋에 세션(방 정보) 저장
//    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    @Builder
//    public ChatRoomDto(ChatRoom chatRoom){
//        this.roomNo = chatRoom.getRoomNo();
//        this.name = chatRoom.getName();
//        chatRoom.builder()
//                .roomNo(roomNo)
//                .name(name)
//                .build();
//    }
//    // 이렇게 하면 service에서 createRoom 할 때 오류남
//
//    public void handleActions(WebSocketSession session, ChatMsgDto chatMsgDto, ChatService chatService){
//        if(chatMsgDto.getType().equals(ChatMsgDto.MsgType.ENTER)){
//            sessions.add(session);
//            chatMsgDto.setContent(chatMsgDto.getSender()+"님이 입장했습니다.");
//        }
//        sendMessage(chatMsgDto, chatService);
//    }
//    public <T> void sendMessage(T message, ChatService chatService){
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
//    }
}
