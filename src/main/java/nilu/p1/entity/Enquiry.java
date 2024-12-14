package nilu.p1.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="enquiry_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String stuName;
	private String stuPhone;
	@Column(unique = true)
	private String email;
	private String classmode;
	private String course;
	private String status;
	@CreationTimestamp
	private LocalDate  createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="counsellor_id")
	private Counsellor cou;

}

