package org.flfmitlab.jhipster5web3j.service.impl;

import org.flfmitlab.jhipster5web3j.messaging.ConsumerChannel;
import org.flfmitlab.jhipster5web3j.messaging.Greeting;
import org.flfmitlab.jhipster5web3j.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	private final Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);


    @StreamListener(ConsumerChannel.CHANNEL)
    public void consume(Greeting greeting) {
        log.info("Received message: {}.", greeting.getMessage());
    }
}
