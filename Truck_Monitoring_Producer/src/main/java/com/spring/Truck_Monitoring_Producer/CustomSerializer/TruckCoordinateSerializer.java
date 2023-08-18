package com.spring.Truck_Monitoring_Producer.CustomSerializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TruckCoordinateSerializer implements Serializer<TruckCoordinates> {

	public byte[] serialize(String topic, TruckCoordinates data) {
		byte[] response=null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response= objectMapper.writeValueAsString(data).getBytes();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

}
