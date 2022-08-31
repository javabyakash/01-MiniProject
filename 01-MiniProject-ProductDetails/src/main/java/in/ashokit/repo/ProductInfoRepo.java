package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.model.ProductInfo;

public interface ProductInfoRepo extends JpaRepository<ProductInfo, Integer> {

}
