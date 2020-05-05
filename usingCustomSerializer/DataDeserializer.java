import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.errors.SerializationException;

import java.nio.ByteBuffer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class DataDeserializer implements Deserializer<Data>{
	private String encoding = "UTF-8";

	@Override
	public void configure(Map<String, ?> configs,boolean isKey){
		// Nothing to configure
	}

	@Override
	public Data deserialize(String topic,byte[] data){
		try{

			if(data == null){
				System.out.println("null receive for deserialization");
				return null;
			}

			ByteBuffer buffer = ByteBuffer.wrap(data);
			int id = buffer.getInt();

			int sizeofName = buffer.getInt();
			byte[] name = new byte[sizeofName];

			buffer.get(name);
			String dataName = new String(name,encoding);

			int age = buffer.getInt();
			int dateSize = buffer.getInt();
			byte[] date = new byte[dateSize];
			buffer.get(date);

			String dateString = new String(date,encoding);
			DateFormat df = new SimpleDateFormat("EEE MMM ddHH:mm:ss Z yyyy");

			return new Data(id,dataName,age,df.parse(dateString));

		}catch(Exception e){
			e.printStackTrace();
			throw new SerializationException("error while deserialization");
		}
	}


	@Override
	public void close(){}
}
