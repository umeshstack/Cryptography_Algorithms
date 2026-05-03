import java.util.Scanner;

public class Hill {
    
    static int modInverse(int a)
    {
        a = ((a % 26) + 26) % 26; // handle negative
        for(int x = 1; x < 26; x++)
            if((a * x) % 26 == 1)
                return x;
        return -1;
    }

    
    static int[][] inverse2x2(int[][] key)
    {
        int a = key[0][0];
        int b = key[0][1];
        int c = key[1][0];
        int d = key[1][1];

        int det = (a*d - b*c) % 26;
        if(det < 0) det += 26;

        int invDet = modInverse(det);

        int[][] inv = new int[2][2];

        inv[0][0] = d;
        inv[0][1] = -b;
        inv[1][0] = -c;
        inv[1][1] = a;

        
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                inv[i][j] = (inv[i][j] * invDet) % 26;
                if(inv[i][j] < 0)
                    inv[i][j] += 26;
            }
        }

        return inv;
    }

    
    public static String encrypt(String text, int[][] key)
    {
        StringBuilder result = new StringBuilder();

        // Padding if odd length
        if(text.length() % 2 != 0)
            text += "x";

        for(int i = 0; i < text.length(); i += 2)
        {
            int a = text.charAt(i) - 'a';
            int b = text.charAt(i+1) - 'a';

            int x = (key[0][0]*a + key[0][1]*b) % 26;
            int y = (key[1][0]*a + key[1][1]*b) % 26;

            result.append((char)(x + 'a'));
            result.append((char)(y + 'a'));
        }
        return result.toString();
    }

    
    public static String decrypt(String text, int[][] key)
    {
        int[][] inv = inverse2x2(key);
        return encrypt(text, inv);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Encrypt\n2. Decrypt");
        int ch = sc.nextInt(); sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine().toLowerCase();

        int[][] key = new int[2][2];
        System.out.println("Enter 2x2 key matrix:");
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                key[i][j] = sc.nextInt();

        if(ch == 1)
            System.out.println("Encrypted: " + encrypt(text, key));
        else
            System.out.println("Decrypted: " + decrypt(text, key));

        sc.close();
    }
}