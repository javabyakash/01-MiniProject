package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.model.UserDetails;

public interface UserDetailsReqRepo extends JpaRepository<UserDetails, Integer> {
	
}	
