package in.kpmg.cm.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HospitalDto {
	
	private String hospId;
	private String hospName;
	private String hsCode;
	private String hospType;
	private String hospitalAddress;
	private Integer stateId;
	private String stateName;
	private Integer districtCode;
	private String districtName;
	private Integer hospitalMandalCode;
	private String hospitalMandal;
	private String geCodeLatitude;
	private String geCodeLongitude;
	private String mithraContact;
	private String hospContactNo;
	private String specialties;


}
