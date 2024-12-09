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
import in.kpmg.cm.repo.MainRepo;
import in.kpmg.cm.dto.rsp.preauthDto;
@Service
public class MainService {

	@Autowired
	private MainRepo mainRepo;
	
	@Autowired 
	private IncrementalDataRepo incrementalDataRepo;
	
	public List<HospitalDto> getHospitalDetails() {
		try {
			List<Object[]> results = mainRepo.findHospitals();
			List<HospitalDto> hospitalDtos = new ArrayList<>();

			for (Object[] row : results) {
				HospitalDto dto = new HospitalDto();

				dto.setHospId(row[0] != null ? (String) row[0] : null);
				dto.setHospName(row[1] != null ? (String) row[1] : null);
				dto.setHsCode(row[2] != null ? (String) row[2] : null);
				dto.setHospType(row[3] != null ? (String) row[3] : null);
				dto.setHospitalAddress(row[4] != null ? (String) row[4] : null);
				dto.setStateId(row[5] != null ? ((BigDecimal) row[5]).intValue() : null);
				dto.setStateName(row[6] != null ? (String) row[6] : null);
				dto.setDistrictCode(row[7] != null ? ((BigDecimal) row[7]).intValue() : null);
				dto.setDistrictName(row[8] != null ? (String) row[8] : null);
				
				dto.setHospitalMandalCode(row[9] != null ? ((BigDecimal) row[9]).intValue() : null);
				dto.setHospitalMandal(row[10] != null ? (String) row[10] : null);
				dto.setGeCodeLatitude(row[11] != null ? row[11].toString() : null);
				dto.setGeCodeLongitude(row[12] != null ? row[12].toString() : null);
				dto.setMithraContact(row[13] != null ? (String) row[13] : null);
				dto.setHospContactNo(row[14] != null ? (String) row[14] : null);
				dto.setSpecialties(row[15] != null ? (String) row[15] : null);

				hospitalDtos.add(dto);
			}

			return hospitalDtos;
		} catch (Exception e) {
			System.err.println("Error executing query: " + e.getMessage());
			throw e;
		}
	}


	public List<PackagesDto> getPackagesHandler() {
		try {
		List<Object[]> results = mainRepo.findPackages();
		List<PackagesDto> procedureDtos = new ArrayList<>();

		for (Object[] row : results) {
			PackagesDto dto = new PackagesDto();
			
			dto.setSurgeryCode(row[0] != null ? (String) row[0] : null);        // surgery_code
		    dto.setSurgeryName(row[1] != null ? (String) row[1] : null);        // surgery_name
		    dto.setProcedureCode(row[2] != null ? (String) row[2] : null);      // procedure_code
		    dto.setProcedureName(row[3] != null ? (String) row[3] : null);      // procedure_name
		    dto.setProcedureType(row[4] != null ? (String) row[4] : null);      // procedure_type
		    dto.setTreatmentType(row[5] != null ? (String) row[5] : null);      // treatment_type (calculated)

		    // Mapping reservedCategory (Character)
		    if (row[6] != null) {
		        dto.setReservedCategory(((Character) row[6]));  // Casting the value to Character
		    } else {
		        dto.setReservedCategory(null);  // Handle nulls
		    }

		    // BigDecimal fields (procedure_amount, aasara_amount)
		    dto.setProcedureAmount(row[7] != null ? (BigDecimal) row[7] : BigDecimal.ZERO); // procedure_amount
		    dto.setAasaraAmount(row[8] != null ? (BigDecimal) row[8] : BigDecimal.ZERO);  

			procedureDtos.add(dto);
		}

		return procedureDtos;
		}catch(Exception e) {
			System.err.println("Error executing query: " + e.getMessage());
			throw e;
		}
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
	
//	public List<IncrementalResponseDto> getIncrementHandler() {
//		List<IncrementalResponseDto[]> results = incrementalDataRepo.getIncrementalData();
//	    List<preauthDto> preatuhDtos = new ArrayList<>();
//
//	    return preatuhDtos;
//	}
	
}
