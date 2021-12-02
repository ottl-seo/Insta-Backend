package efub.insta.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import efub.insta.domain.ChatRoom;
import efub.insta.domain.Like;
import efub.insta.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChatRoomResponseDto {
    private String roomNo;
    private String roomName;
    private UserDto sender;
    private UserDto receiver;

    @Builder
    public ChatRoomResponseDto(ChatRoom chatRoom){
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
    }
}
