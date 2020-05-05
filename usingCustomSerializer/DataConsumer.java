import org.apache.kafka.clients.consumer.*;
import java.util.*;

public class DataConsumer{
	public static void main(String[] args){
		String topic = "DataSupplier";

		Properties property = new Properties();
		property.put("bootstrap.servers","localhost:9092");
		property.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		property.put("value.deserializer","DataDeserializer");
		property.put("group.id","someName");

		Consumer<String,Data> consumer = new KafkaConsumer<>(property);
		consumer.subscribe(Arrays.asList(topic));

		while(true){
			ConsumerRecords<String,Data> records = consumer.poll(100);
			for(ConsumerRecord<String,Data> record:records){
				System.out.println(record.value().getName());
			}
		}

	}
}