package nilu.p1.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Counsellor_Data")
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Counsellor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  counsellorId;
    private String name;
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
    @CreatedDate
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updateDate;




}

