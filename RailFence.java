import java.util.Scanner;

public class RailFence {

    // ENCRYPTION
    public static String encrypt(String text, int key)
    {
        if(key == 1) return text;

        StringBuilder[] rail = new StringBuilder[key];
        for(int i=0;i<key;i++)
            rail[i] = new StringBuilder();

        int row = 0;
        boolean down = true;

        for(char ch : text.toCharArray())
        {
            rail[row].append(ch);

            if(row == 0) down = true;
            else if(row == key-1) down = false;

            row += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : rail)
            result.append(sb);

        return result.toString();
    }

    // DECRYPTION
    public static String decrypt(String cipher, int key)
    {
        if(key == 1) return cipher;

        char[][] rail = new char[key][cipher.length()];

        // Step 1: mark zig-zag path
        int row = 0;
        boolean down = true;

        for(int col = 0; col < cipher.length(); col++)
        {
            rail[row][col] = '*';

            if(row == 0) down = true;
            else if(row == key-1) down = false;

            row += down ? 1 : -1;
        }

        // Step 2: fill characters
        int index = 0;
        for(int i=0;i<key;i++)
        {
            for(int j=0;j<cipher.length();j++)
            {
                if(rail[i][j] == '*' && index < cipher.length())
                {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        // Step 3: read zig-zag
        StringBuilder result = new StringBuilder();
        row = 0;
        down = true;

        for(int col = 0; col < cipher.length(); col++)
        {
            result.append(rail[row][col]);

            if(row == 0) down = true;
            else if(row == key-1) down = false;

            row += down ? 1 : -1;
        }

        return result.toString();
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Encrypt\n2. Decrypt");
        int ch = sc.nextInt(); sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key: ");
        int key = sc.nextInt();

        if(ch == 1)
            System.out.println("Encrypted: " + encrypt(text, key));
        else if(ch == 2)
            System.out.println("Decrypted: " + decrypt(text, key));
        else
            System.out.println("Invalid choice");

        sc.close();
    }
}