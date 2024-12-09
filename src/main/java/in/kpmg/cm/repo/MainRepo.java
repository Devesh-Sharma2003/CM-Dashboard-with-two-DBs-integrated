package in.kpmg.cm.repo;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.cm.dto.rsp.HospitalDto;

@Repository
public class MainRepo {
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<Object[]> findHospitals() {

		String sql = "SELECT ah.hosp_id, ah.hosp_name, ah.hosp_disp_code hs_code, case when ah.hosp_type = 'G'"
				+ " then 'Government' else 'Corporate' end as hosp_type, ah.hosp_addr1 ||''|| ah.hosp_addr2 "
				+ "||''|| ah.hosp_addr3 as hospital_address, al.lgd_code as state_id, al.loc_name as state_name,"
				+ " DIS.LGD_CODE AS District_Code, DIS.LOC_NAME AS district_name, mand.lgd_code AS "
				+ "hospital_mandal_code, mand.loc_name AS hospital_mandal, aeh.ge_code_latitude, "
				+ "aeh.ge_code_longitude,ah.cug_no mithra_contact, ah.hosp_contact_no, "
				+ "(SELECT LISTAGG(ash.speciality_id,',') WITHIN GROUP (ORDER BY ash.speciality_id) "
				+ "FROM asrim_hosp_speciality ash WHERE ash.hosp_id = ah.hosp_id AND ash.phase_id = 6 "
				+ "and ash.renewal = 10 AND ash.is_active_flg = 'Y') AS specialties FROM asrim_hospitals "
				+ "ah left join asrit_empnl_hospinfo aeh on ah. HOSP_EMPNL_REF_NUM = aeh.hospinfo_id LEFT "
				+ "JOIN asrim_locations DIS ON ah.dist_id = DIS.LOC_ID LEFT JOIN asrim_locations mand ON "
				+ "mand.loc_id = aeh.mandal LEFT JOIN asrim_locations al on al.loc_id = dis.loc_parnt_id "
				+ "where ah.hosp_active_yn = 'Y' ORDER BY DIS.LGD_CODE";

		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList(); // Returns List<Object[]>

	}

	public List<Object[]> findPackages() {
		String sql ="select adm.dis_main_id surgery_code, ADM.DIS_MAIN_NAME surgery_name, as1.surgery_id"
				+ " procedure_code, AS1.SURGERY_DESC procedure_name, as1.proc_type procedure_type, case when"
				+ " upper(as1.dis_main_id) like 'S%' then 'Surgical' when upper(as1.dis_main_id) like 'M%' "
				+ "then 'Medical' end as Treatment_Type, CASE WHEN EXISTS (SELECT 1 FROM asrim_surg_phase asp2"
				+ " WHERE asp2.surg_id = as1.surgery_id AND asp2.renewal = '10' AND asp2.govt_yn = 'Y') THEN"
				+ " 'Y' ELSE 'N' END AS reserved_category, as1.surgery_amt procedure_amount,as1.postops_amt "
				+ "aasara_amount from asrim_disease_main adm left join asrim_surgery as1 on as1.dis_main_id = "
				+ "adm.dis_main_id left join asrim_surg_phase asp on asp.surg_id = as1.surgery_id where "
				+ "asp.phase_id = 6 and asp.renewal = 10 order by adm.dis_main_id, as1.surgery_id\r\n";
			
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

    
    public List<Object[]> findpreauthstatistics() {
        String sql = "SELECT DISTINCT \r\n"
        		+ "    to_char(add_months(ac.cs_apprv_rej_dt, -3), 'YYYY') \r\n"
        		+ "        || '-' || (to_char(add_months(ac.cs_apprv_rej_dt, -3), 'YYYY') + 1) AS FINANCIAL_YEAR,\r\n"
        		+ "    to_char(add_months(ac.cs_apprv_rej_dt, -3), 'Month') AS MONTH,\r\n"
        		+ "    DIS.loc_name AS district_name, \r\n"
        		+ "    MAN.loc_name AS mandal_name,\r\n"
        		+ "    COUNT(CASE WHEN ac.cs_apprv_rej_dt IS NOT NULL AND ag.group_id = 'CD17' THEN ac.case_id END) AS total_preauth,\r\n"
        		+ "    COUNT(CASE WHEN ah.hosp_type = 'G' AND ac.cs_apprv_rej_dt IS NOT NULL AND ag.group_id = 'CD17' THEN ac.case_id END) AS public_preauth_count,\r\n"
        		+ "    COUNT(CASE WHEN ah.hosp_type = 'C' AND ac.cs_apprv_rej_dt IS NOT NULL AND ag.group_id = 'CD17' THEN ac.case_id END) AS private_preauth_count,\r\n"
        		+ "        SUM(CASE WHEN ac.cs_apprv_rej_dt IS NOT NULL AND ah.hosp_type = 'G' AND ag.group_id = 'CD17' \r\n"
        		+ "            THEN DECODE(acc.case_cmo_aprv_amt,NULL, \r\n"
        		+ "(DECODE(acc.case_ceo_aprv_amt,NULL,acc.case_trust_aprv_amt,0,acc.case_trust_aprv_amt,acc.case_ceo_aprv_amt)\r\n"
        		+ "),acc.case_cmo_aprv_amt)\r\n"
        		+ "            ELSE 0 \r\n"
        		+ "        END) AS public_preauth_amount,       \r\n"
        		+ "    SUM(CASE \r\n"
        		+ "            WHEN ac.cs_apprv_rej_dt IS NOT NULL AND ah.hosp_type = 'C' AND ag.group_id = 'CD17' \r\n"
        		+ "                 THEN DECODE(acc.case_cmo_aprv_amt,NULL, \r\n"
        		+ "(DECODE(acc.case_ceo_aprv_amt,NULL,acc.case_trust_aprv_amt,0,acc.case_trust_aprv_amt,acc.case_ceo_aprv_amt)\r\n"
        		+ "),acc.case_cmo_aprv_amt) \r\n"
        		+ "            ELSE 0 \r\n"
        		+ "        END) AS private_preauth_amount,\r\n"
        		+ "    SUM(CASE \r\n"
        		+ "            WHEN ac.cs_apprv_rej_dt IS NOT NULL AND ag.group_id = 'CD17' \r\n"
        		+ "            THEN DECODE(acc.case_cmo_aprv_amt,NULL, \r\n"
        		+ "(DECODE(acc.case_ceo_aprv_amt,NULL,acc.case_trust_aprv_amt,0,acc.case_trust_aprv_amt,acc.case_ceo_aprv_amt)\r\n"
        		+ "),acc.case_cmo_aprv_amt)\r\n"
        		+ "            ELSE 0 \r\n"
        		+ "        END) AS total_preauth_amount  \r\n"
        		+ "FROM asrit_case ac\r\n"
        		+ "LEFT JOIN asrit_patient ap ON ac.case_patient_no = ap.patient_id\r\n"
        		+ "LEFT JOIN asrit_case_surgery acs ON acs.case_id = ac.case_id\r\n"
        		+ "LEFT JOIN asrit_case_claim acc ON acc.case_id = ac.case_id\r\n"
        		+ "LEFT JOIN asrim_hospitals ah ON ah.hosp_id = ac.case_hosp_code\r\n"
        		+ "left join asrim_case_status_grp ag on ac.case_status = ag.status_id\r\n"
        		+ "LEFT JOIN asrim_locations DIS ON DIS.loc_id = ap.c_district_code\r\n"
        		+ "LEFT JOIN asrim_locations MAN ON MAN.loc_id = ap.c_mandal_code\r\n"
        		+ "where ag.group_id = 'CD17'\r\n"
        		+ "GROUP BY \r\n"
        		+ "    to_char(add_months(ac.cs_apprv_rej_dt, -3), 'YYYY') \r\n"
        		+ "        || '-' || (to_char(add_months(ac.cs_apprv_rej_dt, -3), 'YYYY') + 1),\r\n"
        		+ "    to_char(add_months(ac.cs_apprv_rej_dt, -3), 'Month'),\r\n"
        		+ "    DIS.loc_name,\r\n"
        		+ "    MAN.loc_name\r\n"
        		+ "ORDER BY \r\n"
        		+ "    to_char(add_months(ac.cs_apprv_rej_dt, -3), 'YYYY') \r\n"
        		+ "        || '-' || (to_char(add_months(ac.cs_apprv_rej_dt, -3), 'YYYY') + 1) DESC,\r\n"
        		+ "    to_char(add_months(ac.cs_apprv_rej_dt, -3), 'Month') DESC";

        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    
}
