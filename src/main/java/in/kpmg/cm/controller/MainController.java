package in.kpmg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.cm.dto.rsp.ApiResponse;
import in.kpmg.cm.service.MainService;

@RestController
public class MainController {
	
	@Autowired
	private MainService mainservice;
	
	@GetMapping("/hospitals")
	public ApiResponse<?> hospitalHandler(){
		try {
			return new ApiResponse<>(true, "Deatils fetched successfully!",
					this.mainservice.getHospitalDetails(), HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ApiResponse<>(false, "Something went wrong!",
					null, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	@GetMapping("/procedures")
	public ApiResponse<?> packagesHandler(){
		try {
			return new ApiResponse<>(true,"Details fetched successfully!",
					this.mainservice.getPackagesHandler(), HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ApiResponse<>(false, "Something went wrong!",
					null, HttpStatus.BAD_REQUEST.value());
		}
	}
	@GetMapping("/preauthstatistics")
	public ApiResponse<?>PreauthHandler(){
		try {
			return new ApiResponse<>(true,"Details fetched successfully!",
					this.mainservice.getPreauthHandler(), HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ApiResponse<>(false, "Something went wrong!",
					null, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	
	@GetMapping("/healthcarddata")
	public ApiResponse<?> getHealthCardHandler(){
		try {
			return new ApiResponse<>(true,"Details fetched successfully!",
					this.mainservice.getHealthCardHandler(), HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ApiResponse<>(false, "Something went wrong!",
					null, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	@GetMapping("/preauthdetails")
	public ApiResponse<?> getPreauthDetailsHandler(){
		try {
			return new ApiResponse<>(true,"Details fetched successfully!",
					this.mainservice.getPreauthDetailsHandler(), HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ApiResponse<>(false, "Something went wrong!",
					null, HttpStatus.BAD_REQUEST.value());
		}
	}
	@GetMapping("/incrementalData")
	public ApiResponse<?>IncrementHandler(){
		try {
			return new ApiResponse<>(true,"Details fetched successfully!",
					this.mainservice.getIncrementHandler(), HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ApiResponse<>(false, "Something went wrong!",
					null, HttpStatus.BAD_REQUEST.value());
		}
	}
	

}
