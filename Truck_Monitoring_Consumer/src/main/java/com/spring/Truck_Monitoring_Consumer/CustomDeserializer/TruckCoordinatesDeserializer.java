package com.spring.Truck_Monitoring_Consumer.CustomDeserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TruckCoordinatesDeserializer implements Deserializer<TruckCoordinates> {

	public TruckCoordinates deserialize(String topic, byte[] data) {
		ObjectMapper objectMapper = new ObjectMapper();
		TruckCoordinates value=null;
		try {
			value = objectMapper.readValue(data, TruckCoordinates.class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
