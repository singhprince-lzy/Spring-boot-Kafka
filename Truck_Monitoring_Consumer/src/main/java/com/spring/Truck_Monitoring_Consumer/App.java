package com.spring.Truck_Monitoring_Consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.spring.Truck_Monitoring_Consumer.CustomDeserializer.TruckCoordinates;
import com.spring.Truck_Monitoring_Consumer.CustomDeserializer.TruckCoordinatesDeserializer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		props.setProperty("value.deserializer", TruckCoordinatesDeserializer.class.getName());
		props.setProperty("group.id", "TruckMonitoringGroup");

		KafkaConsumer<Integer, TruckCoordinates> consumer = new KafkaConsumer<Integer, TruckCoordinates>(props);
		consumer.subscribe(Collections.singletonList("TruckMonitoringCustomSerializer"));

		ConsumerRecords<Integer, TruckCoordinates> records = consumer.poll(Duration.ofSeconds(25));

		for (ConsumerRecord<Integer, TruckCoordinates> record : records) {
			Integer id = record.key();
			TruckCoordinates tc= record.value();
			System.out.println("Truck Id: "+id);
			System.out.println("Truck Latitude position: "+tc.getLatitude());
			System.out.println("Truck Longitude position: "+tc.getLongitude());

		}

	}
}
