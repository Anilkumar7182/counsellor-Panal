package nilu.p1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nilu.p1.Repositery.CounsellorRepo;
import nilu.p1.Repositery.EnquiryRepo;
import nilu.p1.dto.DashboardResponse;
import nilu.p1.entity.Counsellor;
import nilu.p1.entity.Enquiry;

@Service
public class CounsellorServiceImp implements CounsellorService{
	
//	@Autowired
//	private CounsellorRepo repo;
//	
//	@Autowired
//	private EnquiryRepo  enqRepo;
	
	private CounsellorRepo repo;
	
	private EnquiryRepo  enqRepo;
	
	
	public CounsellorServiceImp(CounsellorRepo repo, EnquiryRepo enqRepo) {
		this.repo = repo;
		this.enqRepo = enqRepo;
	}
	
	
	public Counsellor findByEmail(String email) {	
		return  repo.findByEmail(email);
	}

	@Override
	public boolean register(Counsellor counsellor) {
		Counsellor save = repo.save(counsellor);
		if (save.getCounsellorId() != null) {
			return true;
		} else {
			return false;
		}}

	@Override
	public Counsellor login(String email, String pwd) {
		 
		return repo.findByEmailAndPassword(email, pwd);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		
		DashboardResponse response = new DashboardResponse();
		
		List<Enquiry> enqList = enqRepo.getEnquriesByCounsellorId(counsellorId);
		
		int totalEnq = enqList.size();
		
		int enrolledEnqs =enqList.stream()
				                                     .filter(e -> e.getStatus() .equals("Enrolled"))
		                                              .collect(Collectors.toList())
		                                               .size();
		
		int lostEnqs =enqList.stream()
                                                       .filter(e -> e.getStatus() .equals("Lost"))
                                                        .collect(Collectors.toList())
                                                           .size();
		
		int openEnqs =enqList.stream()
                                                   .filter(e -> e.getStatus() .equals("Open"))
                                                     .collect(Collectors.toList())
                                                        .size();
		
		
		response.setTotalEnqs(totalEnq);
		response.setLostEnqs(enrolledEnqs);
		response.setOpenEnqs(lostEnqs);
		response.setEnrolledEnqs(openEnqs);
		
		return response;
	}

	
	

	
	
	
//	@Override
//	public boolean saveCounsellor(Counsellor consellor) {
//		Counsellor save = repo.save(consellor);
//		
//		return save.getName() != null ;
//	}
//
//	@Override
//	public Counsellor getCounsellor(String email, String password) {
//		
//		return repo.findByEmailAndPassword(email, password);
//	}

}
