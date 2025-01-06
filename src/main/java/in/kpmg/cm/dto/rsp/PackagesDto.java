package in.kpmg.cm.dto.rsp;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface PackagesDto {
	
	String getsurgeryCode();     
    String getsurgeryName();    
    String getprocedureCode();     
    String getprocedureName();    
    String getprocedureType();  
    String gettreatmentType();
    Character getreservedCategory(); 
    BigDecimal getprocedureAmount(); 
    BigDecimal getaasaraAmount();


}
