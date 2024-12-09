//package in.kpmg.cm.dto.rsp;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

package in.kpmg.cm.dto.rsp;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class preauthDto {
	
private String financialYear;
private String month;
private String districtName;
private String mandalName;
private String totalPreauth;
private String publicpreauthCount;
private String privatepreauthCount;
private String publicpreauthAmount;
private String privatepreauthAmount;
private String totalpreauthAmount;

}
