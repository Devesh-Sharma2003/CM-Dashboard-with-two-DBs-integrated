package in.kpmg.cm.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface HospitalDto {
	
	 String gethospId();
	 String gethospName();
	 String gethsCode();
	 String gethospType();
	 String gethospitalAddress();
	 Integer getstateId();
	 String getstateName();
	 Integer getdistrictCode();
	 String getdistrictName();
	 Integer gethospitalMandalCode();
	 String gethospitalMandal();
	 String getgeCodeLatitude();
	 String getgeCodeLongitude();
	 String getmithraContact();
	 String gethospContactNo();
	 String getspecialties();


}
