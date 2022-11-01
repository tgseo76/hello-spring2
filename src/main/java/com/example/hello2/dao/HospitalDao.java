package com.example.hello2.dao;

import com.example.hello2.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    RowMapper<Hospital> rowMapper = (rs, rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setOpenLocalGovernmentCode(rs.getInt("open_local_government_code"));
        hospital.setManagementNumber(rs.getString("management_number"));
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        hospital.setBusinessStatus(rs.getInt("business_status"));
        hospital.setBusinessStatusCode(rs.getInt("business_status_code"));
        hospital.setPhone(rs.getString("phone"));
        hospital.setFullAddress(rs.getString("full_address"));
        hospital.setRoadNameAddress(rs.getString("road_name_address"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setHealthcareProviderCount(rs.getInt("healthcare_provider_count"));
        hospital.setPatientRoomCount(rs.getInt("patient_room_count"));
        hospital.setTotalNumberOfBeds(rs.getInt("total_number_of_beds"));
        hospital.setTotalAreaSize(rs.getFloat("total_area_size"));
        return hospital;
    };

    public Hospital findById(int id) {
        return this.jdbcTemplate.queryForObject("select * from hospital where id = ?", rowMapper, id);
    }


    public void deleteAll(){
        this.jdbcTemplate.update("delete from hospital");
    }

    public int getCount(){
//        String sql="select count (id) from `hospital`;";
                String sql="select count(id) from hospital;";
        return this.jdbcTemplate.queryForObject(sql,Integer.class);
    }


    //list<Hospital> --11만건
//    INSERT INTO `likelion-db`.`hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area`) VALUES ('1', '의원', '3620000', 'PHMA119993620020041100004', '19990612', '1', '13', '062-515-2875', '광주광역시 북구 풍향동 565번지 4호 3층', '광주광역시 북구 동문대로 24, 3층 (풍향동)', '효치과의원', '치과의원', '1', '0', '0', '52.29');
    public void add(Hospital hospital){
        String sql = "INSERT INTO `likelion-db`.`hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`) " +
                "VALUES (?,?,?, " +
                "?,?,?, " +
                "?,?,?," +
                " ?,?,?," +
                " ?,?,?, " +
                "?);";
//        String sql1 = "INSERT INTO `likelion-db`.`hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area`) VALUES ('2', '의원', '3620000', 'PHMA119993620020041100003', '19990612', '1', '13', '062-515-2875', '광주광역시 북구 풍향동 565번지 4호 3층', '광주광역시 북구 동문대로 24, 3층 (풍향동)', '효치과의원', '치과의원', '1', '0', '0', '52.29');";
        this.jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize()

        );

    }

}
