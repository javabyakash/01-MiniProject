package in.ashokit.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ELIGIBILITY_DTLS")
public class EligibilityDtls {
	@Id
	private Integer eligId;
	private String name;
	private String email;
	private Long mobile;
	private String gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;
}
