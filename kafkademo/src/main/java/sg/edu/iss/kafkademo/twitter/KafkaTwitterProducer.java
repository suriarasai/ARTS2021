package sg.edu.iss.kafkademo.twitter;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class KafkaTwitterProducer {

	public static void main (String[] args) {
		   
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		 
		 props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	     KafkaProducer kafkaProducer = new KafkaProducer(props);
	     
	    	BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1000);
	    	   String consumerKey = "";
	           String consumerSecret = "";
	           String accessToken = "";
	           String accessTokenSecret = "";
	           String topicName = "twitter";
	           
	           ConfigurationBuilder cb = new ConfigurationBuilder();
	           cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
	           .setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);
	           
	           TwitterFactory tf = new TwitterFactory(cb.build());
	           Twitter twitter = tf.getInstance();
	           
	           try {
	        	   Query query = new Query("cncf");
	        	   	query.setLang("en");
	        	   	query.setLocale("en_IN");
	        	   	query.setCount(10);
	        	   	QueryResult result;
	        	   
	        	   	do {
	        	   		result = twitter.search(query);	        	   	
	        	   		List<Status> tweets = result.getTweets();
	        	   		for (Status tweet:tweets) {
	        	   			System.out.println("@"+tweet.getUser().getScreenName()+"-"+tweet.getText() );
	        	   			ProducerRecord<String,String> producerRecord = new ProducerRecord<String, String>(topicName, tweet.getUser().getScreenName(), tweet.getText() );
	        	   			kafkaProducer.send(producerRecord);
	        	   		}
	        	   		
	        	   	}while ((query = result.nextQuery()) != null);
	           }
	           catch(TwitterException te) {
	        	   te.printStackTrace();
	        	   System.out.println("Failed to search tweets"+te.getMessage());
	                System.exit(-1);
	           }

	         
	}
}
