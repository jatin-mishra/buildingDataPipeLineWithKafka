import org.apache.kafka.clients.producer.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class DataProducer{
	public static void main(String[] args) throws Exception{
		String topic = "DataSupplier";

		Properties property = new Properties();
		property.put("bootstrap.servers","localhost:9092");
		property.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		property.put("value.serializer","DataSerializer");

		Producer<String,Data> producer = new KafkaProducer<>(property);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Data dOne = new Data(2,"jatinMishra",45, df.parse("2016-04-03"));

		producer.send(new ProducerRecord<String,Data>(topic,"Scientist",dOne)).get();
		System.out.println("done");
		producer.close();
	}
}