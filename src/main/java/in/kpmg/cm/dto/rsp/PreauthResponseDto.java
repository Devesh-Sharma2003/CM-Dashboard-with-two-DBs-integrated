package in.kpmg.cm.dto.rsp;

public interface PreauthResponseDto {
	
	 String getfinancialYear();
	 String getmonth();
	 String gethospitalDistCode();
	 String gethospitalDist();
	 String gethospitalMandalCode();
	 String gethospitalMandal();
	 
	 String getcaseHospCode();
	 String gethospName();
	 String gethospType();
	 String getspecialityCode();
	 String getcaseCount();
//	 String getprocedureCodes();
	 String getpreauthApprovedAmount();
	 String getclaimsPaidAmount();
	 String getnoOfDischarges();
	 String gettotalClaimsApproved();
	 


}
