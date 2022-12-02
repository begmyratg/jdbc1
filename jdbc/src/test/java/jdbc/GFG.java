package jdbc;

//Java Program to Convert Map to JSON to HashMap

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;

public class GFG {
	public static void main(String[] args){

		HashMap<String, Object> claimPR = new HashMap<String, Object>();
		claimPR.put("Co Claim Number", "ABC123");
		claimPR.put("Date", "03-15-2022");
		claimPR.put("Attn.:", "BYG");
		claimPR.put("Insured", "Akbar Logistics");
		claimPR.put("Policy Number", "Pol123");

		ObjectMapper mapper = new ObjectMapper();
		try { String claimPRJson = mapper.writeValueAsString(claimPR); 
			System.out.println(claimPRJson);
		}catch (JsonGenerationException e) {e.printStackTrace();
		}catch (JsonMappingException e) {e.printStackTrace();
		}catch (IOException e) {e.printStackTrace();
		}
		
		
		
	}
}
