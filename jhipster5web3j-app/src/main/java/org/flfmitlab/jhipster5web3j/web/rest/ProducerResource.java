package org.flfmitlab.jhipster5web3j.web.rest;

import org.flfmitlab.jhipster5web3j.messaging.Greeting;
import org.flfmitlab.jhipster5web3j.messaging.ProducerChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class ProducerResource {
	
	private final Logger log = LoggerFactory.getLogger(ProducerResource.class);

    private MessageChannel channel;

    public ProducerResource(ProducerChannel channel) {
        this.channel = channel.messageChannel();
    }

    @GetMapping("/greetings/{count}")
    @Timed
    public void produce(@PathVariable int count) {
        while(count > 0) {
        	log.debug(count + " messages");
            channel.send(MessageBuilder.withPayload(new Greeting().setMessage("Hello world!: " + count)).build());
            count--;
        }
    }

}
