package efub.insta.dto;

import efub.insta.domain.ChatRoom;
import efub.insta.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ChatRoomDto {
    private String roomNo;
    private String roomName;
    private User user1;
    private User user2;
    private boolean user1Flag;
    private boolean user2Flag;

    public static ChatRoomDto create(String name){
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.setRoomNo(UUID.randomUUID().toString());
        chatRoom.setRoomName(name);
        return chatRoom;
    }

    public void setUserInfo(ChatRoom chatRoom){
        this.user1 = chatRoom.getSender();
        this.user2 = chatRoom.getReceiver();
    }
}
