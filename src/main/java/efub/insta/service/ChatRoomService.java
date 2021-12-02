package efub.insta.service;

import efub.insta.domain.*;
import efub.insta.dto.ChatMsgDto;
import efub.insta.dto.ChatRoomDto;
import efub.insta.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<ChatRoomResponseDto> findAllRoomsBySender(String userId){
        List<ChatRoom> rooms = chatRoomRepository.findBySenderUserId(userId);


//        //Optional<User> user = userRepository.findById(userNo);
//        List<ChatRoom> chatRoomList = chatRoomRepository.findByUserNo(userNo);
//        List<ChatRoomResponseDto> roomResponseDtoList = new ArrayList<>();
//
//        List<ChatRoom> chatRooms = chatRoomRepository.findByUserNo(userNo);
        return rooms.stream()
                .map(ChatRoomResponseDto::new)
                .collect(Collectors.toList());
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

    public ChatRoomResponseDto findRoomByRoomNo(String roomNo){
        ChatRoom room = chatRoomRepository.findChatRoomByRoomNo(roomNo);
        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(room);

        return chatRoomResponseDto;
    }

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
