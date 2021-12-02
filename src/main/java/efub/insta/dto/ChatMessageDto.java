package efub.insta.dto;
import efub.insta.domain.ChatMessage;
import efub.insta.domain.ChatRoom;
import efub.insta.domain.ChatRoomRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ChatMessageDto {
    private int messageNo;
    private ChatRoomDto chatRoom;
    private String nickname;
    private Boolean flag;
    private LocalDateTime sendTime;
    private String message; //메시지 내용

    @Builder
    public ChatMessageDto(ChatMessage chatMessage){
        this.messageNo = chatMessage.getMessageNo();
        this.nickname = chatMessage.getNickname();
        this.flag = chatMessage.getFlag();
        this.sendTime = LocalDateTime.now();
        this.message = message;
        this.chatRoom = ChatRoomDto.builder()
                .roomNo(chatMessage.getChatRoom().getRoomNo())
                .roomName(chatMessage.getChatRoom().getRoomName())
                .sender(chatMessage.getChatRoom().getSender())
                .receiver(chatMessage.getChatRoom().getReceiver())
                .build();
    }
}