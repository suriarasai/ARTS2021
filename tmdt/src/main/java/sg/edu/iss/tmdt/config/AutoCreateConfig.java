package sg.edu.iss.tmdt.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutoCreateConfig {
	
	@Bean
    public NewTopic taxiEvents(){
        return TopicBuilder.name("taxis")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
