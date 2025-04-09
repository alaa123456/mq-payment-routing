package com.bank.payment.mq.routing.listener;

import com.bank.payment.mq.routing.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.mockito.Mockito.*;
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MqListenerTest {

    static final GenericContainer<?> ibmMq;

    static {
        ibmMq = new GenericContainer<>(DockerImageName.parse("icr.io/ibm-messaging/mq:latest"))
                .withExposedPorts(1414)
                .withEnv("LICENSE", "accept")
                .withEnv("MQ_QMGR_NAME", "QM1")
                .withEnv("MQ_APP_USER", "app")
                .withEnv("MQ_APP_PASSWORD", "passw0rd")
                .withEnv("MQ_ADMIN_USER", "admin")
                .withEnv("MQ_ADMIN_PASSWORD", "passw0rd");
        ibmMq.start();
    }

    @DynamicPropertySource
    static void setMqProperties(DynamicPropertyRegistry registry) {
        registry.add("ibm.mq.queueManager", () -> "QM1");
        registry.add("ibm.mq.channel", () -> "DEV.APP.SVRCONN");
        registry.add("ibm.mq.connName", () -> ibmMq.getHost() + "(" + ibmMq.getMappedPort(1414) + ")");
        registry.add("ibm.mq.user", () -> "app");
        registry.add("ibm.mq.password", () -> "passw0rd");
    }


    @Autowired
    private JmsTemplate jmsTemplate;

    @MockBean
    private MessageService messageService;

    @Test
    public void shouldReceiveMessageAndCallService() throws InterruptedException {
        // GIVEN
        String testMessage = "Hello from Test!";
        jmsTemplate.convertAndSend("DEV.QUEUE.1", testMessage);

        // WHEN
        Thread.sleep(3000);

        // THEN
        verify(messageService, times(1)).saveMessage(eq(testMessage));
    }
}
