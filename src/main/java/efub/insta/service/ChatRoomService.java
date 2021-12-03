package efub.insta.service;

import efub.insta.domain.*;
import efub.insta.dto.ChatMsgDto;
import efub.insta.dto.ChatRoomDto;
import efub.insta.dto.ChatRoomListDto;
import efub.insta.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMsgRepository chatMsgRepository;
    private final UserRepository userRepository;

    @Transactional
    public String calcTime(LocalDateTime before, LocalDateTime after){
        long time = ChronoUnit.SECONDS.between(before, after);
        StringBuilder sb=new StringBuilder();
        String unit = "";
        if(time>3600*12){
            time = ChronoUnit.DAYS.between(before, after);
            unit = "일전";
        }
        if(time<(3600*12)){
            time= ChronoUnit.HOURS.between(before, after);
            unit = "시간전";
        }
        else if(time<3600){
            time = ChronoUnit.MINUTES.between(before, after);

            unit = "분전";
        }
        if(time<60){
            unit = "초전";
        }

        sb.append(Long.toString(time)).append(unit);

        return sb.toString();
    }

    @Transactional
    public List<ChatRoomListDto> findAllRoomsBySender(String userId){
        List<ChatRoom> rooms = chatRoomRepository.findBySenderUserId(userId);
        List<ChatRoomListDto>  roomResponseDtoList = new ArrayList<>();
        for(ChatRoom room:rooms){
            String roomNo = room.getRoomNo();
            List<ChatMsgDto> chatMsgDtoList = getMsgList(roomNo);
            String lastContent = chatMsgDtoList.get(chatMsgDtoList.size() - 1).getContent();
            LocalDateTime time = chatMsgDtoList.get(chatMsgDtoList.size() - 1).getSendTime();
            LocalDateTime now = LocalDateTime.now();
            String calcTime = calcTime(time, now);

            ChatRoomListDto chatRoomResponseDto = new ChatRoomListDto(room, lastContent, calcTime);
            roomResponseDtoList.add(chatRoomResponseDto);
        }

//        //Optional<User> user = userRepository.findById(userNo);
//        List<ChatRoom> chatRoomList = chatRoomRepository.findByUserNo(userNo);
//        List<ChatRoomResponseDto> roomResponseDtoList = new ArrayList<>();
//
//        List<ChatRoom> chatRooms = chatRoomRepository.findByUserNo(userNo);
        return roomResponseDtoList;
    }

    public String createChatRoom(ChatRoomDto chatRoomDto){
        ChatRoom room = new ChatRoom(
                chatRoomDto.getRoomNo(),
                chatRoomDto.getRoomName(),
                chatRoomDto.getSender(),
                chatRoomDto.getReceiver()
        );

        chatRoomRepository.save(room);
        return room.getRoomNo();
    }

//    public List<ChatRoomResponseDto> findAllRooms(){
//        List<ChatRoom> chatRooms = chatRoomRepository.findByRoomNo();
//        List<ChatRoomResponseDto> chatRoomDtoList = new ArrayList<>();
//        for(ChatRoom room : chatRooms){
//            ChatRoomResponseDto chatRoomDto = ChatRoomResponseDto.builder()
//                    .roomNo(room.getRoomNo())
//                    .roomName(room.getRoomName())
//                    .sender(room.getSender())
//                    .receiver(room.getReceiver())
//                    .build();
//
//            chatRoomDtoList.add(chatRoomDto);
//        }
//        return chatRoomDtoList;
//    }
//
//    public ChatRoomResponseDto findRoomByRoomNo(String roomNo){
//        ChatRoom room = chatRoomRepository.findChatRoomByRoomNo(roomNo);
//        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(room);
//
//        return chatRoomResponseDto;
//    }

    @Transactional
    public List<ChatRoomResponseDto> findAllRooms(){
        return chatRoomRepository.findAll().stream().map(room -> new ChatRoomResponseDto(room)).collect(Collectors.toList());
    }

    public List<ChatMsgDto> getMsgList(String roomNo){
        List<ChatMsgDto> chatMsgDtoList = chatMsgRepository.findAll().stream().map(chatMsg -> new ChatMsgDto(chatMsg))
                .filter(chatMsg -> chatMsg.getRoomNo().equals(roomNo)).sorted(Comparator.comparing(ChatMsgDto::getSendTime))
                .collect(Collectors.toList());
        return chatMsgDtoList;
    }

    public String getLastMsg(String roomNo){
        List<ChatMsgDto> chatMsgDtoList = getMsgList(roomNo);
        ChatMsgDto lastMsg = chatMsgDtoList.get(chatMsgDtoList.size() - 1);
        return lastMsg.getContent();
    }

    public List<String[]> getLastMsgList(){
        List<ChatRoomDto> chatRoomList = chatRoomRepository.findAll().stream().map(chatRoom -> new ChatRoomDto(chatRoom)).collect(Collectors.toList());
        List<String[]> chatInfoList = new ArrayList<>();
        for(ChatRoomDto chatRoomDto : chatRoomList){
            String[] info = new String[2];
            info[0] = chatRoomDto.getReceiver().getUserId();
            info[1] = getLastMsg(chatRoomDto.getRoomNo());
            chatInfoList.add(info);
        }
        return chatInfoList;
    }
}
