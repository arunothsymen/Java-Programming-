public class lab2_2 {
    public static void main(String[] args) {
        // Creating a StringBuffer
        StringBuffer stringBuffer = new StringBuffer("Hello");

        // 1. append()
        stringBuffer.append(" World");
        System.out.println("1. Append: " + stringBuffer);

        // 2. insert()
        stringBuffer.insert(6, " Java");
        System.out.println("2. Insert: " + stringBuffer);

        // 3. replace()
        stringBuffer.replace(6, 11, " Programming");
        System.out.println("3. Replace: " + stringBuffer);

        // 4. delete()
        stringBuffer.delete(6, 17);
        System.out.println("4. Delete: " + stringBuffer);

        // 5. charAt()
        char charAtIndex = stringBuffer.charAt(0);
        System.out.println("5. CharAt: " + charAtIndex);

        // 6. setCharAt()
        stringBuffer.setCharAt(0, 'J');
        System.out.println("6. SetCharAt: " + stringBuffer);

        // 7. length()
        int length = stringBuffer.length();
        System.out.println("7. Length: " + length);

        // 8. capacity()
        int capacity = stringBuffer.capacity();
        System.out.println("8. Capacity: " + capacity);

        // 9. ensureCapacity()
        stringBuffer.ensureCapacity(20);
        System.out.println("9. EnsureCapacity: " + stringBuffer);

        // 10. toString()
        String str = stringBuffer.toString();
        System.out.println("10. ToString: " + str);

        // 11. substring(int start)
        String substring1 = stringBuffer.substring(2);
        System.out.println("11. Substring(2): " + substring1);

        // 12. substring(int start, int end)
        String substring2 = stringBuffer.substring(2, 6);
        System.out.println("12. Substring(2, 6): " + substring2);
    }
}