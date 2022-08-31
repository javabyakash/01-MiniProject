package in.ashokit.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_DETAILS_MASTER")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder(value = {"First Name","Last Name","Email","Phone","created_date","updated_date","created_by","update_by","status","Address","Product Info"})
public class UserDetails {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "first_name")
	@JsonProperty(value = "First Name")
	private String firstName;
	@Column(name = "last_name")
	@JsonProperty(value = "Last Name")
	private String lastName;
	@Column(name = "email")
	@JsonProperty(value = "Email")
	private String email;
	@Column(name = "phone")
	@JsonProperty(value = "Phone")
	private Long phone;
	
	@Column(name = "created_date",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name = "updated_date",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "status")
	private String status;
	
	//Integration
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addrs_id_fk")
	@JsonProperty(value = "Address")
	private Address address;
	
	@OneToOne(targetEntity = ProductInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_info_id_fk")
	@JsonProperty(value = "Product Info")
	private ProductInfo productInfo;
	
	
}
