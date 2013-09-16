package tse.lr1;

import java.util.Scanner;

/**
 * Калькулятор
 * @author aNNiMON
 */
public class Calculator {
    
    public static void main() {
        double result = 0.0d;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные:");
        do {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) return;
            
            char operation = '+';
            double operandA = 0, operandB = 0;
            
            // Блок парсинга
            String[] items = line.split(" ");
            switch(items.length) {
                case 1:
                    String param1 = items[0].trim();
                    if (isNumber(param1)) {
                        // Если пришло число, складываем с предыдущим результатом
                        operandA = result;
                        operandB = getNumber(param1);
                    } else if (isOperator(param1)) {
                        // Если оператор, то выполняем операцию (res oper res)
                        operandA = result;
                        operandB = result;
                        operation = getOperator(param1);
                    }
                    break;
                case 2:
                    param1 = items[0].trim();
                    String param2 = items[1].trim();
                    if (isOperator(param1) && isNumber(param2)) {
                        operandA = result;
                        operandB = getNumber(param2);
                        operation = getOperator(param1);
                    }
                    break;
                case 3:
                    param1 = items[0].trim();
                    param2 = items[1].trim();
                    String param3 = items[2].trim();
                    if (isNumber(param1) && isOperator(param2) && isNumber(param3)) {
                        operandA = getNumber(param1);
                        operandB = getNumber(param3);
                        operation = getOperator(param2);
                    }
                    break;
                default:
                    // Принимается максимум три параметра (a oper b)
                    System.out.println("Error");
                    return;
            }
            
            // Блок расчёта
            switch(operation) {
                case '+':
                    result = operandA + operandB;
                    break;
                case '-':
                    result = operandA - operandB;
                    break;
                case '*':
                    result = operandA * operandB;
                    break;
                case '/':
                    if (operandB == 0.0) {
                        System.out.println("Error: ∞");
                    } else result = operandA / operandB;
                    break;
                case '^':
                    result = Math.pow(operandA, operandB);
                    break;
            }
            
            System.out.println("Результат: " + result);
            
        } while (true);
    }
    
    private static Double getNumber(String text) {
        try {
            return Double.parseDouble(text);
        } catch (Exception ex) {
            return 0.0d;
        }
    }
    
    private static boolean isNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    private static char getOperator(String text) {
        return text.charAt(0);
    }
    
    private static boolean isOperator(String text) {
        if (text.length() != 1) return false;
        
        char symbol = text.charAt(0);
        char[] operations = "+-*/^".toCharArray();
        for (char ch : operations) {
            if (ch == symbol) return true;
        }
        return false;
    }
}
