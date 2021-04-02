package sg.edu.iss.tmdt.controller;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sg.edu.iss.tmdt.model.Taxi;
import sg.edu.iss.tmdt.producer.TaxiProducer;

@RestController
@Slf4j
public class TaxiController {

	@Autowired
	TaxiProducer taxiProducer;

	@PostMapping("/v1/taxi")
	public ResponseEntity<Taxi> postLibraryEvent(@RequestBody @Valid Taxi taxi)
			throws JsonProcessingException, ExecutionException, InterruptedException {

		// invoke kafka producer
		taxiProducer.sendTaxi_Approach2(taxi);
		return ResponseEntity.status(HttpStatus.CREATED).body(taxi);
	}

	// PUT
	@PutMapping("/v1/taxi")
	public ResponseEntity<?> putLibraryEvent(@RequestBody @Valid Taxi taxi)
			throws JsonProcessingException, ExecutionException, InterruptedException {

		if (taxi.getTmdtid() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the TMDT ID");
		}

		taxiProducer.sendTaxi_Approach2(taxi);
		return ResponseEntity.status(HttpStatus.OK).body(taxi);
	}

}
