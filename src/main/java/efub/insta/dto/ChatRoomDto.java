package efub.insta.dto;

import efub.insta.domain.ChatRoom;
import efub.insta.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Builder
public class ChatRoomDto {
    private String roomNo;
    private String roomName;
    private User sender;
    private User receiver;
/*
    public static ChatRoomDto create(String name){
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.setRoomNo(UUID.randomUUID().toString());
        chatRoom.setRoomName(name);
        return chatRoom;
    }

 */

    public void setUserInfo(ChatRoom chatRoom){
        this.sender = chatRoom.getSender();
        this.receiver = chatRoom.getReceiver();
    }
/*
    @Builder
    public ChatRoomDto(ChatRoom chatRoom){
        this.roomNo = chatRoom.getRoomNo();
        this.roomName = chatRoom.getRoomName();
        this.sender = chatRoom.getSender();
        this.receiver = chatRoom.getReceiver();
    }

 */
}
