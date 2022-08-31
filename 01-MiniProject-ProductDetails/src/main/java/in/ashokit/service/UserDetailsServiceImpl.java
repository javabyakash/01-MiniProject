package in.ashokit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.model.UserDetails;
import in.ashokit.repo.UserDetailsReqRepo;

@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

	@Autowired
	private UserDetailsReqRepo userDetailsRepo;

	@Override
	public Boolean saveUserWithProduct(UserDetails userDtlsrequest) {
		UserDetails savedUserDtls = userDetailsRepo.save(userDtlsrequest);

		return savedUserDtls.getId() != null ? true : false;
	}

	@Override
	public UserDetails getUserDetailsById(Integer id) {
		Optional<UserDetails> opt = userDetailsRepo.findById(id);
		UserDetails detailsRequest;
		if (opt.isPresent()) {
			detailsRequest = opt.get();
		} else {
			return null;
		}
		return detailsRequest;

	}

}
