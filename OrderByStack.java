import java.util.Stack;
public class OrderByStack{
	
	public static void main(String[] args) {
		Stack<Integer> mainStack = new Stack<>();
		Stack<Integer> tempStack = new Stack<>();

		mainStack.push(2);
		mainStack.push(4);
		mainStack.push(1);
		mainStack.push(12);
		mainStack.push(22);
		mainStack.push(10);
		mainStack.push(12);

		System.out.println("Main before " + mainStack);

		tempStack.push(mainStack.pop());
		
		while(!mainStack.empty()){
			if(tempStack.peek()<=mainStack.peek()){
				tempStack.push(mainStack.pop());
			}else{
				int temp = 0;
				temp = mainStack.pop();
				while(!tempStack.empty() && tempStack.peek()>temp){
					mainStack.push(tempStack.pop());
				}
				tempStack.push(temp);
			}
			System.out.println("Main inner " + mainStack);
			System.out.println("Temp inner " + tempStack);
		}

		System.out.println("Main final " + mainStack);
		System.out.println("Temp final " + tempStack);

	}

}