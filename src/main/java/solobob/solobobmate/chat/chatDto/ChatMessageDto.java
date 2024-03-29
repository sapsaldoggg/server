package solobob.solobobmate.chat.chatDto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private Long roomId;

    private String sender;

    private String message;

}
