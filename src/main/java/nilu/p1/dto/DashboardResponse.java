package nilu.p1.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class DashboardResponse {
	
	private Integer totalEnqs;
	private Integer openEnqs;
	private Integer enrolledEnqs;
	private Integer lostEnqs;
	
	

}
