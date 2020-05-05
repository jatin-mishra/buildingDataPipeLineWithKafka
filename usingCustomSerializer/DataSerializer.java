import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.errors.SerializationException;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.nio.ByteBuffer;

public class DataSerializer implements Serializer<Data>{
	private String encoding = "UTF-8";

	@Override
	public void configure(Map<String,?> config,boolean isKey){

	}


	@Override
	public byte[] serialize(String topic,Data data){

		int sizeofName;
		int sizeofDate;
		byte[] name;
		byte[] date;

		if(data == null)
			return null;

		try{
			name = data.getName().getBytes(encoding);
			sizeofName = name.length;

			date = data.getDate().toString().getBytes(encoding);
			sizeofDate = date.length;

			ByteBuffer buffer = ByteBuffer.allocate(4+4+sizeofName+4+4+sizeofDate);
			buffer.putInt(data.getId());
			buffer.putInt(sizeofName);
			buffer.put(name);
			buffer.putInt(data.getAge());
			buffer.putInt(sizeofDate);
			buffer.put(date);

			return buffer.array();
		}catch(Exception e){
			throw new SerializationException("error while serializing");
		}

	}

	@Override
	public void close(){}

}