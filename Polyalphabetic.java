import java.util.Scanner;

public class Polyalphabetic {

    public static String encrypt(String text,String key)
    {
        StringBuilder result = new StringBuilder();

        for(int i=0;i<text.length();i++)
        {
            char ch1 = text.charAt(i);
            char ch2 = key.charAt(i % key.length());

            result.append((char)((((ch1-'a')+(ch2-'a'))%26)+'a'));
        }
        return result.toString();
    }

    public static String decrypt(String text,String key)
    {
        StringBuilder result = new StringBuilder();

        for(int i=0;i<text.length();i++)
        {
            char ch1 = text.charAt(i);
            char ch2 = key.charAt(i % key.length());

            result.append((char)((((ch1-'a')-(ch2-'a')+26)%26)+'a'));
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
        String key = sc.nextLine();

        if(ch==1)
            System.out.println("Encrypted: "+encrypt(text,key));
        else
            System.out.println("Decrypted: "+decrypt(text,key));
    }
}
