package efub.insta.dto;

import efub.insta.domain.ChatRoom;
import efub.insta.domain.User;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
public class ChatRoomDto {
    private String roomNo;
    private String roomName;
    private User sender;
    private User receiver;

    public static ChatRoomDto create(String name){
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.setRoomNo(UUID.randomUUID().toString());
        chatRoom.setRoomName(name);
        return chatRoom;
    }

    public void setUserInfo(ChatRoom chatRoom){
        this.sender = chatRoom.getSender();
        this.receiver = chatRoom.getReceiver();
    }

    public ChatRoomDto(ChatRoom chatRoom){
        this.roomNo = chatRoom.getRoomNo();
        this.roomName = chatRoom.getRoomName();
        this.sender = chatRoom.getSender();
        this.receiver = chatRoom.getReceiver();
    }
}
