import java.util.Scanner;

class Ceaser {

    public static String encrypt(String text,int key)
    {
        StringBuilder result = new StringBuilder();
        for(char ch : text.toCharArray())
        {
            if(Character.isUpperCase(ch))
                result.append((char)(((ch - 'A' + key)%26) + 'A'));
            else if(Character.isLowerCase(ch))
                result.append((char)(((ch - 'a' + key)%26) + 'a'));
            else
                result.append(ch);
        }
        return result.toString();
    }

    public static String decrypt(String text,int key)
    {
        return encrypt(text, 26 - key); // smart reuse
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

        if(ch==1)
            System.out.println("Encrypted: "+encrypt(text,key));
        else
            System.out.println("Decrypted: "+decrypt(text,key));
    }
}
