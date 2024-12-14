package nilu.p1.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import nilu.p1.Repositery.CounsellorRepo;
import nilu.p1.Repositery.EnquiryRepo;
import nilu.p1.dto.ViewEnqsFilterRequest;
import nilu.p1.entity.Counsellor;
import nilu.p1.entity.Enquiry;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	private  EnquiryRepo  enqRepo;
	
	private CounsellorRepo counRepo;
	
	
	public EnquiryServiceImpl(EnquiryRepo enqRepo, CounsellorRepo counRepo) {
		this.enqRepo = enqRepo;
		this.counRepo = counRepo;
	}


	
	@Override
	public boolean addEnquiry(Enquiry enq, Integer counserllorId)throws Exception {
		Counsellor counsellor = counRepo.findById(counserllorId).orElse(null);
		if(counsellor == null) {
			throw new Exception("No counsellor found");
		}
		
		// associating counsellor to enquiry
		enq.setCou(counsellor);
		
		Enquiry save = enqRepo.save(enq);
		
		if(save.getEnqId() != null) {
			return true;
		}
		return false;
	}
	

	@Override
	public List<Enquiry> getAllEnquirys(Integer counsellorId) {
		
		return enqRepo.getEnquriesByCounsellorId(counsellorId);
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
	 
		return enqRepo.findById(enqId).orElse(null);
	}
	
	@Override
	public List<Enquiry> getEnquiriesWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
		
		// QBY   implementation (Dynamic Query Preparation)
		
		Enquiry enq = new Enquiry();  // entity
		
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassmode(filterReq.getClassMode());
		}
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassmode(filterReq.getCourseName());
		}
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassmode(filterReq.getEnqStatus());
		}
		
		 Counsellor c = counRepo.findById(counsellorId).orElse(null);
		 enq.setCou(c);
		
		Example<Enquiry> of = Example.of(enq);  // dynamic Query
		
		List<Enquiry> anqList = enqRepo.findAll(of);
		
		return anqList;
	}}
