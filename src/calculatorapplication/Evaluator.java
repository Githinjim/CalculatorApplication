package calculatorapplication;

/**
 * @author Michael Githinji
 * @version CS 480
 * @since 11/2/2016
 */

/*
 *The evaluator class
 */
public class Evaluator {

    private final MyStack stack;
    private final String input;
    private String output = "";

    /**
     * ************************************
     */
    /* assign the values to this method    */
    public Evaluator(String key) {
        input = key;
        int Size = input.length();
        stack = new MyStack(Size);
    }

    /**
     * ******************************************
     * @return output This method is push operator to stack
     */
    public String Convertor() {
        /* using switch to cenvert */
        for (int i = 0; i < input.length(); i++) {
            char evaluate = input.charAt(i);
            switch (evaluate) {
                // if evaluate is "+" or "-", use this case
                case '+':
                case '-':
                    Operator(evaluate, 1);
                    break;
                // if evaluate is "*" or "/", use this case
                case '*':
                case '/':
                    Operator(evaluate, 2);
                    break;
                // if evaluate is "!", use this case    
                case '!':
                    Operator(evaluate, 3);
                    break;
                // if evaluate is "(", use this case
                case '(':
                    stack.push(evaluate);
                    break;
                // if evalutate is ")", use this case
                case ')':
                    endParenthesis(evaluate);
                    break;
                // if evaluate is numbers
                default:
                    output = output + evaluate;
                    break;
            }
        }
        while (!stack.isEmpty()) {
            output = output + stack.pop();
        }
        System.out.println(output);
        return output;
    }

    /**
     * ********************************************************
     * @param operator
     * @param process1 This method puts operators to the stack
     */
    public void Operator(char operator, int process1) {
        while (!stack.isEmpty()) {
            char lastOp = stack.pop();
            if (lastOp == '(') { // If there is a left parentheses
                stack.push(lastOp);
                break;
            } else {
                int process2;
                if (lastOp == '+' || lastOp == '-') {
                    process2 = 1;
                } else {
                    process2 = 2;
                }
                if (process2 < process1) {
                    stack.push(lastOp);
                    break;
                } else {
                    output = output + lastOp;
                }
            }
        }
        stack.push(operator);
    }

    /**
     * *********************************************************
     */
    /**
     * Check inside the parenthesis / from Convertor method
     *
     * @param leftParenthesis
     */
    public void endParenthesis(char leftParenthesis) {
        while (!stack.isEmpty()) {
            char check = stack.pop();
            if (check == '(') {
                break;
            } else {
                output = output + check;
            }
        }
    }

    /**
     * ****************************************************************************
     */
    /**
     * This method is meant to evaluate numbers to get the required results.
     *
     * @return
     */
    public static String Compute(String expression) {
        java.util.Stack<Double> stack = new java.util.Stack();
        char[] character = expression.toCharArray();
        String currentVariable = "";
        for (int i = 0; i < expression.length(); i++) {
            if ((character[i] >= '0' && character[i] <= '9') || character[i] == '.') { // get a number until select non number
                currentVariable = currentVariable + character[i];
            } else {
                if (currentVariable.length() > 0) {   // push the currentVariable to stack as double
                    double currentDouble = Double.parseDouble(currentVariable);
                    stack.push(currentDouble);
                    currentVariable = "";
                }
                if (character[i] == '+') {    // in case, "+" select
                    double number1 = stack.pop();
                    double number2 = stack.pop();
                    double result = number1 + number2;
                    System.out.println(result);
                    stack.push(result);
                } else if (character[i] == '-') {  // in case, "-" select
                    double number1 = stack.pop();
                    double number2 = stack.pop();
                    double result = number2 - number1;
                    stack.push(result);
                } else if (character[i] == '*') {  // in case, "*" select
                    double number1 = stack.pop();
                    double number2 = stack.pop();
                    double result = number1 * number2;
                    stack.push(result);
                } else if (character[i] == '/') {  // in case, "/" select
                    double number1 = stack.pop();
                    double number2 = stack.pop();
                    try {   // check Numbers which illegal or not
                        double result = number2 / number1;
                        if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) {  // Just in case, divided by 0.0
                            throw new ArithmeticException();
                        }
                        stack.push(result);
                    } catch (ArithmeticException e) {    // divided by zero exception
                        System.out.println("ArithmeticException occured!");
                        return "DNE";
                    }

                } else if (character[i] == '^') {  // in case, "+" select
                    double number1 = stack.pop();
                    if (number1 >= 1 && number1 <= 12) {    // this calculator can use factrorial when a number is greater than 0 and less than 13
                        int fint = (int) Math.floor(number1);
                        stack.push((double) factorial(fint));
                    } else {    // block factorial when a number is not greater than 0 and less than 13
                        System.out.println("ERROR! Factorial cannot use in this case");
                        return "DNE";
                    }
                }
            }
        }
        if (stack.isEmpty()) {   // for single number 
            return currentVariable;
        } else {    // get result from stack and return as String
            double FinalNum = stack.pop();
            String Result = String.valueOf(FinalNum);
            return Result;
        }
    }

    /**
     * **************************************************************
     */
    /* Difine factorial using recursive                              */
    private static int factorial(int n) {
        if (n == 0) {    // this is base case
            return 1;
        } else {    // this is recursive case
            return factorial(n - 1) * n;
        }
    }

    /**
     * **************************************************************
     */
    /* Make Stack for Numbers                                        */
    class MyStack {

        private final int Size;
        private final char[] Stack;
        private int lastPut;

        public MyStack(int max) {
            Size = max;
            Stack = new char[Size];
            lastPut = -1;
        }

        public void push(char i) {  // in case, push new number
            Stack[++lastPut] = i;
        }

        public char pop() {    // in case, pop last number
            return Stack[lastPut--];
        }

        public char peek() {    // in case, check last namber
            return Stack[lastPut];
        }

        public boolean isEmpty() {    // in case check stack is empty or not
            return (lastPut == -1);
        }
    }
}
