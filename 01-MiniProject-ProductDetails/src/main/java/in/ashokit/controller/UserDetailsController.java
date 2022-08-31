package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.model.UserDetails;
import in.ashokit.service.IUserDetailsService;

@RestController
public class UserDetailsController {
	
	@Autowired
	private IUserDetailsService userDetailsService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUserDtls(@RequestBody UserDetails detailsRequest){	
		String msg = "";
		Boolean isUserWithProductSaved = userDetailsService.saveUserWithProduct(detailsRequest);
		if(isUserWithProductSaved)
			msg = "User With Product Details Are Saved!";
		else
			msg = "User With Product Details Are Failed To Save!";
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<?> getUserDtlsById(@PathVariable Integer id){
		UserDetails userDetailsById = userDetailsService.getUserDetailsById(id);
		if(userDetailsById==null) 
			return new ResponseEntity<String>("User Id Not Found!",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<UserDetails>(userDetailsById,HttpStatus.OK);
	}
}
