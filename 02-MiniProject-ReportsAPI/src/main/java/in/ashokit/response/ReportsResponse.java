package in.ashokit.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportsResponse {
	private Integer eligId;
	private String name;
	private String email;
	private Long mobile;
	private String gender;
	private Long ssn;
}
