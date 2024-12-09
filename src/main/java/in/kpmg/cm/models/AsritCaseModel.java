package in.kpmg.cm.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asrit_case")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsritCaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "case_id")
	private String caseId;

	@Column(name = "case_no")
	private String caseNo;

	@Column(name = "CASE_HOSP_CODE")
	private String caseHospCode;

	
}
