package in.ashokit.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "PRODUCT_INFO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder(value = {"Product Name","Product Brand And Model","Product Model Id","Product Fabrication Year","Purchase Date"})
public class ProductInfo implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "product_name")
	@JsonProperty(value = "Product Name")
	private String productName;
	@Column(name = "prod_brand_model")
	@JsonProperty(value = "Product Brand And Model")
	private String prodBrandAndModel;
	@Column(name = "prod_model_id")
	@JsonProperty(value = "Product Model Id")
	private String prodModelId;
	@Column(name = "prod_fabrication_year")
	@JsonProperty(value = "Product Fabrication Year")
	private String prodFabricationYear;
	@Column(name = "purchase_date")
	@JsonProperty(value = "Purchase Date")
	private LocalDate purchaseDate;
}
