package com.sample.pubsub.controller;

import com.sample.pubsub.config.PubSubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAppController {

    @Autowired
    private PubSubConfig.PubsubOutboundGateway messagingGateway;

    @PostMapping("/publishMessage")
    public ResponseEntity publishMessage(@RequestParam("message") String message) {
        messagingGateway.sendToPubsub(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity sendBulkMessages(@RequestParam("count") int count) {
        for (int i = 1; i <= count; i++) {
            messagingGateway.sendToPubsub("Test Message: " + i);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
