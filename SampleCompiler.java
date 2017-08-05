import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class SampleCompiler{

	public static void main(String args[]) throws IOException{

		System.out.println(findError("C:/Users/Medagedara/Java/TestFile.java"));

	}

	private static String findError(String fileName) throws IOException{

		Stack<String> stack = new Stack<>();
		int line = 1;
		String current;
		boolean errorFould = false;
		String tempRight = "";
		String tempLeft = "";

		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while ((current = bufferedReader.readLine()) != null){		// Loop till last line 
			for (int i = 0; i < current.length(); i++){		// Loop a single line till last character
				if(String.valueOf(current.charAt(i)).matches("[{(\\[]")){		// If an opening bracket add to stack with details
					stack.push(String.valueOf(line + "," + (i + 1) + "," + String.valueOf(current.charAt(i))));
				}
				if(String.valueOf(current.charAt(i)).matches("[})\\]]")){		// If a closing bracket check for a start
					String close = String.valueOf(current.charAt(i));
					if (stack.empty()){		// If the error is a top most block
						return "Error:- No open of -> \"" + close + "\" \n\tLine-no -> " + line + "\n\tCherecter-no -> "
								+ (i + 1);
					}else{
						
						if(errorFould){	// If found an error check whether opening one or closing (refer with "ref")
									// If next left bracket is not equal to next right bracket error is closing bracket else closing bracket
							if(pairNotCorrect(tempLeft.split(",")[2], String.valueOf(current.charAt(i)))) {	
								return "Error:- No close of -> \"" + tempLeft.split(",")[2] + "\" \n\tLine-no -> "
										+ tempLeft.split(",")[0] + "\n\tCherecter-no -> " + tempLeft.split(",")[1];
							}else{
								return "Error:- No open of -> \"" + tempRight.split(",")[2] + "\" \n\tLine-no -> "
										+ tempRight.split(",")[0] + "\n\tCherecter-no -> " + tempRight.split(",")[1];
							}
						}
	/** ref **/				errorFould = pairNotCorrect(stack.peek().split(",")[2], String.valueOf(current.charAt(i)));
						if(errorFould){		// If an error found assign both brackets to temporary variables with details
							tempRight = line + "," + (i + 1) + "," + current.charAt(i);
							tempLeft = stack.peek();
						}
						stack.pop();
					}
				}
			}
			line++;
		}
		
		// If the error is top most last bracket
		if(!stack.empty()){
			return "Error:- No close of -> \"" + stack.peek().split(",")[2] + "\" \n\tLine-no -> "
					+ stack.peek().split(",")[0] + "\n\tCherecter-no -> " + stack.peek().split(",")[1];
		}

		return "";
	}

	/**
	 	Check for opening closing bracket pairs if first is opening of
	 	second return true else false
	 **/
	private static boolean pairNotCorrect(String first, String second){
		switch (first) {
		case "(":
			return (!second.equals(")"));
		case "{":
			return (!second.equals("}"));
		case "[":
			return (!second.equals("]"));
		}
		return false;
	}

}