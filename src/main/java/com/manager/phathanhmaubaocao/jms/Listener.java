package com.manager.phathanhmaubaocao.jms;

import com.manager.phathanhmaubaocao.jms.model.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Component
public class Listener {
    private static Logger log = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "${spring.snv.sync.topic:services.snv.sync.phathanhmaubaocao}")
    // @SendTo("services.name.${spring.application.name}")
    public String receiveMessage(@Payload Demo demo
        , @Headers MessageHeaders headers
        , Message jsonMessage,
                                 Session session) throws JMSException {
        //String messageData = null;
        log.info("received <" + demo + ">");
        return demo.toString();
    }

//	@JmsListener(
//            destination = "Consumer.checking_system.VirtualTopic.Events",
//            selector = "_type = 'ReviewSubmittedEvent'"
//    )
//    public void onEvent(ReviewSubmittedEvent reviewSubmittedEvent) {
//        Check check = new Check(
//                new ReviewId(reviewSubmittedEvent.getReviewId()),
//                reviewSubmittedEvent.getSubject(),
//                reviewSubmittedEvent.getContent()
//        );
//        checkingService.checkReview(check);
//    }
}
