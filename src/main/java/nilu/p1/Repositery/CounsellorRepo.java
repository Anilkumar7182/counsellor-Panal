package nilu.p1.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nilu.p1.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	
	// select * from counsellor_tbl  where email = email and password = password
	public Counsellor findByEmailAndPassword(String email,String password);
	
	// select * from counsellor_tbl where email = email
	public Counsellor findByEmail(String email);

}