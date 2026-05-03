import java.util.Scanner;

public class Monoalphabetic {

    static char[] key = {'N','V','S','R','P','X','O','Z','W','H','K','L','Q','E','T','Y','U','I','A','D','F','G','J','C','B','M'};

    public static String encrypt(String text)
    {
        StringBuilder result = new StringBuilder();

        for(char ch : text.toCharArray())
        {
            if(Character.isUpperCase(ch))
                result.append(key[ch-'A']);
            else if(Character.isLowerCase(ch))
                result.append((char)(key[ch-'a'] + 32));
            else
                result.append(ch);
        }
        return result.toString();
    }

    public static String decrypt(String text)
    {
        StringBuilder result = new StringBuilder();

        for(char ch : text.toCharArray())
        {
            if(Character.isUpperCase(ch))
            {
                for(int i=0;i<26;i++)
                    if(key[i]==ch)
                        result.append((char)(i+'A'));
            }  
            else if(Character.isLowerCase(ch))
            {
                for(int i=0;i<26;i++)
                    if((char)(key[i]+32)==ch)
                        result.append((char)(i+'a'));
            }
            else
                result.append(ch);
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

        if(ch==1)
            System.out.println("Encrypted: "+encrypt(text));
        else
            System.out.println("Decrypted: "+decrypt(text));
    }
}