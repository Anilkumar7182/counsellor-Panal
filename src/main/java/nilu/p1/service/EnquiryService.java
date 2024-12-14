package nilu.p1.service;

import java.util.List;

import nilu.p1.dto.ViewEnqsFilterRequest;
import nilu.p1.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(Enquiry enq,Integer counserllorId) throws Exception;
	
	public List<Enquiry> getAllEnquirys(Integer  counsellorId);
	
	public List<Enquiry> getEnquiriesWithFilter(ViewEnqsFilterRequest  filterReq , Integer counsellorId);
	
	public Enquiry  getEnquiryById(Integer enqId);

}
