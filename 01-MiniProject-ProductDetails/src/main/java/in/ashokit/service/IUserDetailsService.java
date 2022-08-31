package in.ashokit.service;

import in.ashokit.model.UserDetails;

public interface IUserDetailsService {
	public Boolean saveUserWithProduct(UserDetails request);
	public UserDetails getUserDetailsById(Integer id);
}
