package com.example.hello2.parser;

import com.example.hello2.dao.HospitalDao;
import com.example.hello2.domain.Hospital;
import com.example.hello2.service.HospitalService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HospitalParserTest {

    String line1= "1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    HospitalService hospitalService;


//환경변수에 SPRING_DATASOURCE_URL에 AWS 주소입력
    // SPRING_DATASOURCE_PASSWORD = 비밀번호
    @Test//db에 저장
    @DisplayName("10만건 이상 데이터가 파싱 되는지")
    void oneHundreadThousandRows() throws IOException {
        // 서버환경에서 build할 때 문제가 생길 수 있습니다.
        // 어디에서든지 실행할 수 있게 짜는 것이 목표.

        //테스트할때마다 실행되기때문에 주석처리
//        hospitalDao.deleteAll(); //db 지우기
//        String filename = "C:\\Users\\tjxor\\Desktop\\fulldata1.csv";
//        int cnt = this.hospitalService.insertLargeVolumeHospitalData(filename);
//        assertTrue(cnt > 1_000);
//        assertTrue(cnt > 10_000);
//        System.out.printf("파싱된 데이터 개수:%d", cnt);
    }
    
//    @Test
//    @DisplayName("10만건 이상 데이터가 파싱 되는지")
//    void oneHundreadThousandRows() throws IOException {
//        // 서버환경에서 build할 때 문제가 생길 수 있습니다.
//        // 어디에서든지 실행할 수 있게 짜는 것이 목표.
//        String filename = "C:\\Users\\tjxor\\Desktop\\fulldata1.csv";
//        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
//        assertTrue(hospitalList.size() > 1000);
//        assertTrue(hospitalList.size() > 10000);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(hospitalList.get(i).getHospitalName());
//        }
//        System.out.printf("파싱된 데이터 개수:%d", hospitalList.size());
//    }



//    @Test
//    @DisplayName("hospital insert 확인")
//    void addAndGet() {
//
//        hospitalDao.deleteAll();
//        assertEquals(0, hospitalDao.getCount());
//        HospitalParser hp = new HospitalParser();
//        Hospital hospital = hp.parse(line1);
//        hospitalDao.add(hospital);
//        assertEquals(1, hospitalDao.getCount());
//
//        Hospital selectedHospital = hospitalDao.findById(hospital.getId());
//        assertEquals(selectedHospital.getId(), hospital.getId());
//        assertEquals(selectedHospital.getOpenServiceName(), hospital.getOpenServiceName());
//        assertEquals(selectedHospital.getHospitalName(), hospital.getHospitalName());
//        // 날짜, float
//
//        assertTrue(selectedHospital.getLicenseDate().isEqual(hospital.getLicenseDate()));
//        assertEquals(selectedHospital.getTotalAreaSize(), hospital.getTotalAreaSize());
//        // findById
//
//    }
    @Test
    @DisplayName("csv 1줄을 hospital로 만드는지")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());

    }






}