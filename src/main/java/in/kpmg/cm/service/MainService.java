package in.kpmg.cm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.kpmg.cm.dto.rsp.HealthcardResponseDto;
import in.kpmg.cm.dto.rsp.HospitalDto;
import in.kpmg.cm.dto.rsp.IncrementalResponseDto;
import in.kpmg.cm.dto.rsp.PackagesDto;
import in.kpmg.cm.dto.rsp.PreauthDetailsResponseDto;
import in.kpmg.cm.dto.rsp.PreauthResponseDto;
import in.kpmg.cm.repo.IncrementalDataRepo;
import in.kpmg.cm.dto.rsp.preauthDto;
@Service
public class MainService {
	
	@Autowired 
	private IncrementalDataRepo incrementalDataRepo;
	
	public List<HospitalDto> getHospitalDetails() {
		return this.incrementalDataRepo.findHospitals();
	}


	public List<PackagesDto> getPackagesHandler() {
		return this.incrementalDataRepo.findPackages();
	}


	
	public List<PreauthResponseDto> getPreauthHandler() {
	List<PreauthResponseDto> results = incrementalDataRepo.getPreauthStatisticsData();
    return results;
    }

	public List<HealthcardResponseDto> getHealthCardHandler() {
	List<HealthcardResponseDto> results = incrementalDataRepo.getHealthCardData();
    return results;
	}
	public List<PreauthDetailsResponseDto> getPreauthDetailsHandler() {
	List<PreauthDetailsResponseDto> results = incrementalDataRepo.getPreauthDetailsData();
    return results;
	}
	
	public List<IncrementalResponseDto> getIncrementHandler() {
		List<IncrementalResponseDto> results = incrementalDataRepo.getIncrementalData();

	    return results;
	}
	
}
