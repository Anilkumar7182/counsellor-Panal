package nilu.p1.service;

import nilu.p1.dto.DashboardResponse;
import nilu.p1.entity.Counsellor;

public interface CounsellorService {
	
//	public boolean saveCounsellor(Counsellor consellor);
//	
//	public Counsellor getCounsellor(String email, String password);
	
	// Service Method
	
	public Counsellor findByEmail(String email);
	
	public boolean register(Counsellor counsellor);
	
	public Counsellor login(String email, String pwd);
	
	public DashboardResponse getDashboardInfo(Integer counsellorId);

}
