import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FastSigmoid {

	/*
	 * A map that approximates the sigmoid function. 
	 */
	public HashMap<Double, Double> sig;
	
	public FastSigmoid() {
		fillMap();
	}

	/*
	 * Populates the map with values. 
	 */	
	public void fillMap() {
		sig = new HashMap<Double, Double>(); 
		
		try {
			List<String> sigmoidData = Files.readAllLines(Paths.get("sigmoidData.txt"), Charset.defaultCharset());
			String[] inputLine;
			for(int i = 0; i < sigmoidData.size(); i++) {
				inputLine = sigmoidData.get(i).split(" ");
				sig.put(Double.parseDouble(inputLine[0]), Double.parseDouble(inputLine[1]));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Round the given input value to two decimal places and get
	 * the resulting output value from the sigmoid map.
	 */	
	public Double getValue(Double input) {
		Double roundFactor = 100.0;
		Double result = input*roundFactor;
		result = (double) Math.round(result);
		result = (Double) result/roundFactor;
		
		if(result > 10.0) {
			result = 10.0;
		} else if(result < -10.0) {
			result = -10.0;
		}
		
		if(result < 0) {
			result = -1.0*(sig.get(-result));
		} else {
			result = sig.get(result);
		}
		
		return 2*result - 1.0;
	}

	/*
	 * Returns the derivative of the sigmoid of the given input value.
	 */	
	public Double derivative(Double input) {
		Double sigValue = getValue(input); 
		return sigValue*(1 - sigValue);
	}
	
}
