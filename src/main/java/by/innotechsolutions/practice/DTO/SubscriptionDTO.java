package by.innotechsolutions.practice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.FluxSink;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionDTO {

    private String nickName;

    private FluxSink<ServerSentEvent> fluxSink;

}