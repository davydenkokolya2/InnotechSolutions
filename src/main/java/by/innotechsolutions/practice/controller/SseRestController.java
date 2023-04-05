package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.SendMessageRequestDTO;
import by.innotechsolutions.practice.DTO.SubscriptionDTO;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SseRestController {
    Map<UUID, SubscriptionDTO> subscriptions = new ConcurrentHashMap<>();

    @GetMapping(path = "/open-sse-stream/{nickName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<ServerSentEvent> openSseStream(@PathVariable String nickName) {
        return Flux.create(fluxSink -> {
            System.out.println("create subscription for " + nickName);

            UUID uuid = UUID.randomUUID();

            fluxSink.onCancel(() -> {
                subscriptions.remove(uuid);
                System.out.println("subscription " + nickName + " was closed");
            });

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO(nickName, fluxSink);
            subscriptions.put(uuid, subscriptionDTO);
            //System.out.println(subscriptions.size());
            SendMessageRequestDTO sendMessageRequestDTO = new SendMessageRequestDTO();
            sendMessageRequestDTO.setMessage("Hello " + nickName);
            ServerSentEvent<SendMessageRequestDTO> helloEvent = ServerSentEvent.builder(sendMessageRequestDTO).build();
            fluxSink.next(helloEvent);
        });
    }

    @PutMapping(path = "/send-message-for-all")
    public void sendMessageForAll(@RequestBody SendMessageRequestDTO requestDTO) {
        ServerSentEvent<SendMessageRequestDTO> event = ServerSentEvent.builder(requestDTO).build();
        subscriptions.forEach(((uuid, subscriptionDTO) -> subscriptionDTO.getFluxSink().next(event)));
    }

    @PutMapping(path = "/send-message-by-name/{nickName}")
    public void sendMessageByName(
            @PathVariable String nickName,
            @RequestBody SendMessageRequestDTO requestDTO
    ) {

        ServerSentEvent<SendMessageRequestDTO> event = ServerSentEvent
                .builder(requestDTO)
                .build();

        subscriptions.forEach((uuid, subscriptionData) -> {
                    if (nickName.equals(subscriptionData.getNickName())) {
                        subscriptionData.getFluxSink().next(event);
                    }
                }
        );
    }
}
