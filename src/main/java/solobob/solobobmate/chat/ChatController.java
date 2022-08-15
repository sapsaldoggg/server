package solobob.solobobmate.chat;

import solobob.solobobmate.auth.config.SecurityUtil;
import solobob.solobobmate.chat.chatDto.ChatDto;
import solobob.solobobmate.chat.chatDto.ChatSendDto;
import solobob.solobobmate.controller.exception.ErrorCode;
import solobob.solobobmate.controller.exception.SoloBobException;
import solobob.solobobmate.domain.Chat;
import solobob.solobobmate.domain.Member;
import solobob.solobobmate.domain.Party;
import solobob.solobobmate.repository.MemberRepository;
import solobob.solobobmate.repository.PartyRepository;
import solobob.solobobmate.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template;
    private final PartyRepository partyRepository;
    private final ChatService chatService;
    private final MemberRepository memberRepository;

    public Member getMember() {
        Member member = memberRepository.findByLoginId(SecurityUtil.getCurrentMemberId()).orElseThrow(
                () -> new SoloBobException(ErrorCode.NOT_FOUND_MEMBER)
        );
        return member;
    }

    // /pub/party/enter 로 요청이 오면, 파티 입장
//    @MessageMapping("/party/enter")   // /pub/party/enter
//    public void enterChat(@RequestBody Long partyId, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        Member member = principalDetails.getMember();
//        Party party = partyRepository.findById(partyId).orElseThrow(
//                () -> new IllegalArgumentException("파티가 존재하지 않습니다")
//        );
//        Chat chat = chatService.create(party, member, member.getNickname() + "님이 입장하였습니다.");
//
//        ChatSendDto chatSendDto = ChatSendDto.builder()
//                .partyId(chat.getParty().getId())
//                .sender(chat.getMember().getNickname())
//                .message(chat.getMessage())
//                .sendTime(chat.getSendTime())
//                .build();
//
//        template.convertAndSend("/sub/party/"+partyId, chatSendDto);
//    }

    // /pub/party/message 로 요청이 오면, 메시지 전송 (SEND)
//    @MessageMapping("/party/message")  // /pub/party/message
//    public void messageChat(@RequestBody ChatDto chatDto) {
//        Member member = getMember();
//        Party party = partyRepository.findById(chatDto.getPartyId()).orElseThrow(
//                () -> new SoloBobException(ErrorCode.NOT_FOUND_PARTY)
//        );
//        Chat chat = chatService.create(party, member, chatDto.getMessage());
//
//        ChatSendDto chatSendDto = ChatSendDto.builder()
//                .partyId(chat.getParty().getId())
//                .sender(chat.getMember().getNickname())
//                .message(chat.getMessage())
//                .sendTime(chat.getSendTime())
//                .build();
//
//        template.convertAndSend("/sub/party/" + chatDto.getPartyId()+"/chat", chatSendDto);
//    }
}