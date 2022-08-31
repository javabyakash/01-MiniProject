package in.ashokit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS_TAB")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder(value = {"Street","City","State","Country","Pin Code"})
public class Address implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "addrs_id")
	private Integer addrsId;
	@Column(name = "street_addresss")
	@JsonProperty(value = "Street")
	private String streetAddress;
	@Column(name = "city")
	@JsonProperty(value = "City")
	private String city;
	@Column(name = "state")
	@JsonProperty(value = "State")
	private String state;
	@Column(name = "country")
	@JsonProperty(value = "Country")
	private String country;
	@Column(name = "pincode")
	@JsonProperty(value = "Pin Code")
	private Long pinCode;
}
