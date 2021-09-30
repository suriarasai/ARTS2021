package sg.edu.iss.kafkademo.twitter;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class KafkaTwitterStream {

	public static void main(String[] args) throws InterruptedException {
		Properties props = new Properties();

		  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams_kafka_app_id");
		  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		  
		  StreamsConfig streamsConfig = new StreamsConfig(props);
		  
		  Serde<String> stringSerde = Serdes.String();
		  
		  StreamsBuilder builder = new StreamsBuilder();
		  
		  //builder.stream("test1",Consumed.with(stringSerde, stringSerde))
		  
		  KStream<String, String> simpleFirstStream = builder.stream("twitter",Consumed.with(stringSerde, stringSerde));
		  KStream<String, String> upperCasedStream = simpleFirstStream.filter((key, value) -> value.contains("cncf"));
				  //.mapValues (v -> v.toUpperCase());
				  
		  upperCasedStream.to( "twitter-output",Produced.with(stringSerde, stringSerde));
		  
		  KafkaStreams kafkaStreams = new KafkaStreams(builder.build(),streamsConfig);
		  kafkaStreams.start();
		  Thread.sleep(35000);

		  kafkaStreams.close();

	}

}
