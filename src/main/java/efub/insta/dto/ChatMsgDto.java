package efub.insta.dto;

import efub.insta.domain.ChatMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMsgDto {
    public enum MsgType { //메시지 타입: 입장, 채팅
        ENTER,
        TALK
    }
    private MsgType type; //메시지 타입
    private String roomNo; //방번호
    private Boolean flag;
    private String sender; //보낸 사람
    private LocalDateTime sendTime;
    private String content; //메시지 내용

    @Builder
    public ChatMsgDto(ChatMsg chatMsg){
        if(chatMsg.getType() != MsgType.ENTER) this.type = MsgType.TALK;
        this.roomNo = chatMsg.getChatRoom().getRoomNo();
        this.flag = chatMsg.getFlag();
        this.sender = chatMsg.getSender();
        this.sendTime = chatMsg.getSendTime();
        this.content = chatMsg.getContent();
    }

    public int compareTo(ChatMsgDto o) {
        LocalDateTime time1 = o.getSendTime();
        return time1.isBefore(this.getSendTime())? -1:0;
    }
}
