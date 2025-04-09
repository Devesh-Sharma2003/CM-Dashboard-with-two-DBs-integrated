package in.kpmg.cm.aarogya.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import in.kpmg.cm.argsri.models.AsritCaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "asrim_dropdown")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsrimDropDownModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dd_dtl_id")
	private String ddDtlId;
	
	@Column(name="dd_dtl_name")
	private  String ddDtlName;
	
	@Column(name="cmb_hdr_id")
	private String cmbHdrId;

}
