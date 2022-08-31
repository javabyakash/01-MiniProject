package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
