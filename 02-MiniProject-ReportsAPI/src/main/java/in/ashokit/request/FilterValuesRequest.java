package in.ashokit.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterValuesRequest {
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
}
