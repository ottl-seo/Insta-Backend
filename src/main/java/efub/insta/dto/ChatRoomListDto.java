package efub.insta.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import efub.insta.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChatRoomListDto {
    private String roomNo;
    private String roomName;
    private UserDto sender;
    private UserDto receiver;
    private String contents;
    private String time;
//
//    public void setLastTime(ChatRoom chatRoom, ChatMsg chatMsg){
//        this.time = "16시간 전";
//    }

//    public void setContents(ChatRoom chatRoom, ChatMsg chatMsg){
//        this.contents = chatMsg.get
//    }

    @Builder
    public ChatRoomListDto(ChatRoom chatRoom, String contents, String time){
        this.roomNo = chatRoom.getRoomNo();
        this.roomName = chatRoom.getRoomName();
        this.sender = UserDto.builder()
                .userNo(chatRoom.getSender().getUserNo())
                .userId(chatRoom.getSender().getUserId())
                .fileSize(chatRoom.getSender().getFileSize())
                .originalFileName(chatRoom.getSender().getOriginalFileName())
                .filePath(chatRoom.getSender().getFilePath())
                .build();
        this.receiver = UserDto.builder()
                .userNo(chatRoom.getReceiver().getUserNo())
                .userId(chatRoom.getReceiver().getUserId())
                .fileSize(chatRoom.getReceiver().getFileSize())
                .originalFileName(chatRoom.getReceiver().getOriginalFileName())
                .filePath(chatRoom.getReceiver().getFilePath())
                .build();
        this.contents = contents;
        this.time = time;
    }
}
