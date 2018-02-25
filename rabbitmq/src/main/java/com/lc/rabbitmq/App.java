package com.lc.rabbitmq;

import java.util.UUID;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RabbitMQ Service
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableRabbit
// @RabbitListener(queues="boot_test")
@RabbitListener(
		// containerFactory = "rabbitListenerContainerFactory",
		bindings = @QueueBinding(value = @Queue(value = "boot_test", durable = "true"), exchange = @Exchange(value = "boot_test_exchange", type = ExchangeTypes.DIRECT), key = "boot_test_routekey")
// admin = "rabbitAdmin"
)
public class App implements RabbitTemplate.ConfirmCallback, CommandLineRunner {

	@Value("${test.name}")
	private String name;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@RequestMapping(value = "/name")
	public String getName() {
		return name;
	}

	@Override
	public void run(String... arg0) throws Exception {
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot-routingKey", "hello 章颖 ~ ！......",
				correlationId);
		rabbitTemplate.convertAndSend("boot_test_exchange", "boot_test_routekey", "hello 李聪 ~ ！......", correlationId);
	}

	@Scheduled(cron = "5/2 * * * * ?")
	public void schedule() throws Exception {
		run();
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println(correlationData);
		System.out.println(ack);
		System.out.println(cause);
	}

	@RabbitHandler
	public void handler1(Object msg) {
		System.out.println(msg.getClass());
		Message message = (Message) msg;
		System.out.println("李聪的 Topic recive msg : " + new String(message.getBody()));
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
