package com.patientManagement.notificationService.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

import java.util.Arrays;
import java.util.Base64;

@Service
@Slf4j
public class SQSConsumerService {
    private static final String QUEUE_NAME = "patient-sqs";

    @SqsListener(QUEUE_NAME)
    public void listen(String patientEventMessage) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(patientEventMessage);
        PatientEvent patientEvent = PatientEvent.parseFrom(bytes);
        log.info(patientEvent.toString());
    }
}
