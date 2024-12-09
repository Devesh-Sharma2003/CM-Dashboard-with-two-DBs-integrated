package in.kpmg.cm.dto.rsp;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackagesDto {
	
	private String surgeryCode;     
    private String surgeryName;    
    private String procedureCode;     
    private String procedureName;    
    private String procedureType;  
    private String treatmentType;
    private Character reservedCategory; 
    private BigDecimal procedureAmount; 
    private BigDecimal aasaraAmount;


}
