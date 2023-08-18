package com.spring.Truck_Monitoring_Producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.spring.Truck_Monitoring_Producer.CustomSerializer.TruckCoordinates;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("value.serializer", "com.spring.Truck_Monitoring_Producer.CustomSerializer.TruckCoordinateSerializer");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

		KafkaProducer<Integer, TruckCoordinates> producer = new KafkaProducer(props);
		
		TruckCoordinates t=new TruckCoordinates();
		t.setId(1);
		t.setLatitude("22.5726N");
		t.setLongitude("88.3639E");
		ProducerRecord<Integer, TruckCoordinates> record = record = new ProducerRecord("TruckMonitoringCustomSerializer", t.getId(), t);

		try {
				producer.send(record);
				System.out.println("Message Sent SuccessFully");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}

	}
}
