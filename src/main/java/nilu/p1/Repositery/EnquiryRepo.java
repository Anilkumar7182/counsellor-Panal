package nilu.p1.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nilu.p1.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
    
    @Query(value = "SELECT * FROM enquiry_tbl WHERE counsellor_id = :counsellorId", nativeQuery = true)
    List<Enquiry> getEnquriesByCounsellorId(@Param("counsellorId") Integer counsellorId);
}


//public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{
//	
//@Query(value = "select * from enquiry_tbl where counsellor_id = counsellorId" , nativeQuery = true)
//public List<Enquiry> getEnquriesByCounsellorId(@Param("counsellorId") Integer counsellorId);
//	
//	
//}
