package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApp {

    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class);
    }

    @Bean
    public CommandLineRunner demo(EventDataRepository repository) {
        return (args) -> {
            String eventId = "abcd123";
            repository.save(new EventData(eventId, 5, "APP_HOST", "12345"));

            EventData eventData = repository.findByEventId(eventId);

            logger.info("Event ID is: " + eventData.getEventId());
            logger.info("Is Alerted: " + eventData.getIsAlerted());
        };

    }
}
