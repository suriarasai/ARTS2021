package sg.edu.iss.tmdt.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "taxis")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Taxi {
	
	private int tmdtid;
	private int driverid;
	private String taxiNumber;
	private Double plat;
	private Double plon;
	private char availability;

}
