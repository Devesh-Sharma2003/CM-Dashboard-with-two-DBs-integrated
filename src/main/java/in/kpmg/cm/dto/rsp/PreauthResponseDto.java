package in.kpmg.cm.dto.rsp;

public interface PreauthResponseDto {
	 String getfinancialYear();
	 String getmonth();
	 String getcaseHospCode();
	 String gethospName();
	 String gethospitalDistCode();
	 String gethospitalDist();
	 String gethospitalMandalCode();
	 String gethospitalMandal();
	 String gethospType();
//	 String getcaseCount();
	 String getspecialityCode();
//	 String getnoOfDischarges();
	 String gettotalPreauthsApproved();
	 String getpreauthApprovedAmount();
	 String gettotalClaimsApproved();
	 String getclaimsPaidAmount();

}
