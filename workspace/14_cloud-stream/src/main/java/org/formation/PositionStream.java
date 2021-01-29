package org.formation;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PositionStream {

	String OUTPUT = "positions-out";
	

	
	@Output(OUTPUT)
	MessageChannel outboundPositions();
}
