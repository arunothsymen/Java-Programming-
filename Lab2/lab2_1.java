import java.util.Arrays;

public class lab2_1 {

    public static void main(String[] args) {
        String text = "We realizes that while our workers were thriving, the surrounding villages were still suffering."
                +
                "It became our goal to uplift their quality of life as well." +
                "I remember seeing a family of 4 on a motorbike in the heavy Bombay rain — I knew I wanted to do more for these families who were risking their lives for lack of an alternative"
                +
                "The alternative mentioned here is the Tata Nano, which soon after came as the world’s cheapest car on retail at a starting price of only Rs 1 lakh."
                +
                "These were the words of Ratan Tata in a recent post by Humans of Bombay which formed the basis of his decision to come up with a car like Tata Nano.";

        char firstChar = text.charAt(0);
        System.out.println("charAt(): " + firstChar);

        String anotherText = "Another text for comparison";
        int compareResult = text.compareTo(anotherText);
        System.out.println("compareTo(): " + compareResult);

        String combinedText = text.concat(" " + anotherText);
        System.out.println("concat(): " + combinedText);

        boolean containsWord = text.contains("workers");
        System.out.println("contains(): " + containsWord);

        boolean endsWithPeriod = text.endsWith(".");
        System.out.println("endsWith(): " + endsWithPeriod);

        boolean isEqual = text.equals(anotherText);
        System.out.println("equals(): " + isEqual);

        boolean isEqualIgnoreCase = text.equalsIgnoreCase(anotherText);
        System.out.println("equalsIgnoreCase(): " + isEqualIgnoreCase);

        String formattedText = String.format("Formatted text: %s", text);
        System.out.println("format(): " + formattedText);

        byte[] textBytes = text.getBytes();
        System.out.println("getBytes(): " + Arrays.toString(textBytes));

        char[] charArray = new char[text.length()];
        text.getChars(0, text.length(), charArray, 0);
        System.out.println("getChars(): " + Arrays.toString(charArray));

        int indexOfThrive = text.indexOf("thriving");
        System.out.println("indexOf(): " + indexOfThrive);

        String internedText = text.intern();
        System.out.println("intern(): " + internedText);

        boolean isEmpty = text.isEmpty();
        System.out.println("isEmpty(): " + isEmpty);

        String[] words = text.split(" ");
        String joinedText = String.join("-", words);
        System.out.println("join(): " + joinedText);

        int lastIndexOfVillages = text.lastIndexOf("villages");
        System.out.println("lastIndexOf(): " + lastIndexOfVillages);

        int textLength = text.length();
        System.out.println("length(): " + textLength);

        String replacedText = text.replace("workers", "employees");
        System.out.println("replace(): " + replacedText);

        String regexReplacedText = text.replaceAll("\\b(\\w+)\\b", "$1_replaced");
        System.out.println("replaceAll(): " + regexReplacedText);

        String[] splitText = text.split(" ");
        System.out.println("split(): " + Arrays.toString(splitText));

        boolean startsWithWe = text.startsWith("We");
        System.out.println("startsWith(): " + startsWithWe);

        String substringText = text.substring(10, 30);
        System.out.println("substring(): " + substringText);

        char[] charArrayFromText = text.toCharArray();
        System.out.println("toCharArray(): " + Arrays.toString(charArrayFromText));

        String lowerCaseText = text.toLowerCase();
        System.out.println("toLowerCase(): " + lowerCaseText);

        String upperCaseText = text.toUpperCase();
        System.out.println("toUpperCase(): " + upperCaseText);

        String trimmedText = text.trim();
        System.out.println("trim(): " + trimmedText);

        int intValue = 42;
        String stringValue = String.valueOf(intValue);
        System.out.println("valueOf(): " + stringValue);
    }
}