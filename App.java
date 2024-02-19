import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class App {

    public static void main(String[] args) throws IOException, ArithmeticException, NumberFormatException {
        System.out.println("Enter two arabic or roman numberals and mathematical operator between them.");
        Scanner sc = new Scanner (System.in);
        String input = sc.nextLine(); 
        sc.close();

        try {
            char operation = detectOper(input);          
            String[] numbers = parse(input);
            boolean isArabic = Roman.isArabic(numbers);
            boolean isRoman = Roman.isRoman(numbers);
            boolean isException = Roman.detectException(isArabic, isRoman);

            String [] toArabic = new String[numbers.length];
            String toRoman = new String();

            if (isArabic == true && isException == true) {
                int out = calculate(numbers, operation, isRoman);
                System.out.println(out);
            } if (isRoman == true && isException == true) {
                for (int i = 0; i < numbers.length; i++){
                    toArabic[i] = Integer.toString(Roman.toArabic(numbers[i]));
                }
                int out = calculate(toArabic, operation, isRoman);
                toRoman = Roman.toRoman(out);
                System.out.println(toRoman);
            }

        } catch (IOException e) {
            System.out.println("You have to enter two operands (from 0 to 10 of arabic) or (from I to X of roman) and one math operator between them.");
        } catch (ArithmeticException e) {
            System.out.println("You can't divide by zero.");
        } catch (UnsupportedOperationException e) {
            System.out.println("Math operation not found.");
        } catch (NumberFormatException e) {
            System.out.println("You have to enter only roman or arabic numb.");
        } 
    }

        public static String[] parse(String input) throws IOException{
            String inputWithoutSpaces = input.replaceAll(" ","");
            String[] inputNumbsArr = inputWithoutSpaces.split("\\+|\\*|/|-");
            if (inputNumbsArr.length !=2){
                throw new IOException();
            } else {
                return inputNumbsArr;
            }
        }
        
        public static char detectOper(String str) throws UnsupportedOperationException, IOException {
 
            Pattern pattern = Pattern.compile("\\+|-|/|\\*");
            Matcher matcher = pattern.matcher(str);

            int count = 0;
            String str2 = new String();
            char out = '\0';
        
            while (matcher.find()) {
                ++count;
                if (count != 1) {
                    throw new IOException();
                } else {
                    str2 = matcher.group();   
                    out = str2.charAt(0);
                }
            }
            return out;
        } 

        public static int calculate(String[] numbs, char oper, boolean isRoman) throws ArithmeticException, IOException {
            int out = 0;
            int [] numbers = new int[numbs.length];
            if (numbs.length != 2){
                throw new IOException();
            } else {
                for (int i = 0; i < numbs.length; i++) {
                    numbers[i] = Integer.parseInt(numbs[i]);
            } 
                if ((0 < numbers[0]) && (numbers[0] < 11) && (0 < numbers[1]) && (numbers[0] < 11)) {
                    switch (oper) {
                        case '+':
                            out = numbers[0] + numbers[1];
                            break;
                        case '-':
                            out = numbers[0] - numbers[1];
                            if (isRoman == true && out < 1) {
                                throw new IOException();
                            } else {
                                break;
                            }
                        case '*':
                            out = numbers[0] * numbers[1];
                            break;
                        default:
                            if (numbers[1] == 0){
                                throw new ArithmeticException();
                            } else {
                            out = numbers[0] / numbers[1];
                            break;
                            }
                    }  
                } else {
                    throw new IOException();
                } 
            }
            return out;
        }

    public static class Roman {
        private static int[] intervals = {0,1,4,5,9,10,40,50,90,100,400,500,900,1000,1100};
        private static String[] numerals= {"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M", "MC"};
        
        public static int findFloor(final int number, final int firstIndex, final int lastIndex) {
            if (firstIndex==lastIndex)
                return firstIndex;    
            if (intervals[firstIndex]==number)
                return firstIndex;
            if (intervals[lastIndex]==number)
                return lastIndex;
            final int median = (lastIndex + firstIndex)/2; 
            if (median == firstIndex) 
                return firstIndex; 
            if (number == intervals[median])
                return median;
            if (number > intervals[median])
                return findFloor(number, median, lastIndex);
            else
                return findFloor(number, firstIndex, median);
        }
        
        public static String toRoman(final int number) {
            int floorIndex = findFloor(number, 0, intervals.length-1);
            if (number == intervals[floorIndex]) 
                return numerals[floorIndex];
            return numerals[floorIndex]+toRoman(number-intervals[floorIndex]); 
        }
            
        public static int toArabic(String roman) {
            int result = 0;
            for (int i = intervals.length-1; i >= 0; i-- ) {
                while (roman.indexOf(numerals[i]) == 0 && numerals[i].length() > 0) {
                    result += intervals[i];
                    roman = roman.substring(numerals[i].length());
                }
            }
            return result;
        }

        public static boolean isRoman(String[] input){
            Pattern patternRoman = Pattern.compile("I|V|X");
            for (int i = 0; i < input.length;){
                Matcher matcherRoman = patternRoman.matcher(input[i]);
                    if (matcherRoman.find()){
                        ++i;
                    } else {
                        return false;
                    }
            }
            return true; 
        }

        public static boolean isArabic(String[] input){
            Pattern patternArabic = Pattern.compile("\\d+");
            for (int i = 0; i < input.length;){
                Matcher matcherArabic = patternArabic.matcher(input[i]);
                    if (matcherArabic.find()){
                        ++i;
                    } else {
                        return false;
                    }
            }
            return true;
        }

        public static boolean detectException(boolean arabic, boolean roman) throws NumberFormatException{
            boolean out = true;
            if (arabic == true && roman == true) {
                throw new NumberFormatException();
            } else if (arabic == false && roman == false) {
                throw new NumberFormatException();
            } else {
                return out;
            }
        }
    }
}  
