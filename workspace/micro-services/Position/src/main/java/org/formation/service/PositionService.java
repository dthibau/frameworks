package org.formation.service;

import java.util.logging.Logger;

import org.formation.PositionStream;
import org.formation.model.Position;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;


@Service
public class PositionService {
    private final PositionStream positionsStreams;

    Logger log = Logger.getLogger(PositionService.class.getName());
    
    public PositionService(PositionStream positionsStreams) {
        this.positionsStreams = positionsStreams;
    }

    public void sendGreeting(final Position position) {
        log.info("Sending position " + position);

        MessageChannel messageChannel = positionsStreams.outboundPositions();
        messageChannel.send(MessageBuilder
                .withPayload(position)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
