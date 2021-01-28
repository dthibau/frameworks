package org.formation.service;

import java.util.logging.Logger;

import org.formation.AverageStream;
import org.formation.model.Position;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class AverageService {
	private final AverageStream averageStream;

    Logger log = Logger.getLogger(AverageService.class.getName());
    
    public AverageService(AverageStream averageStream) {
        this.averageStream = averageStream;
    }
	
    public void sendAverage(Position averagePosition) {
    	log.info("Sending Average position " + averagePosition);

        MessageChannel messageChannel = averageStream.outboundAverages();
        messageChannel.send(MessageBuilder
                .withPayload(averagePosition)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}
}
