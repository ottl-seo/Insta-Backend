package efub.insta.web.dto;

import efub.insta.domain.ChatMessage;
import efub.insta.domain.ChatRoom;
import efub.insta.domain.Post;
import efub.insta.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRequestDto {
    private int messageNo;
    private ChatRoom chatRoom;
    private String nickname;
    private Boolean flag;
    //private LocalDateTime sendTime;
    private String message;

    @Builder
    public ChatRequestDto(ChatMessage entity){
        this.messageNo = entity.getMessageNo();
        this.chatRoom = entity.getChatRoom();
        this.nickname = entity.getNickname();
        this.flag = entity.getFlag();
        this.message = entity.getMessage();
    }
}
