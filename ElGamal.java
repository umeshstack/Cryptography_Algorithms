import java.util.Scanner;

public class ElGamal {

    static long power(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    static long modInverse(long a, long p) {
        a = ((a % p) + p) % p;

        for (long i = 1; i < p; i++) {
            if ((a * i) % p == 1)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== ElGamal Encryption & Decryption ===");

        System.out.print("Enter prime p: ");
        long p = sc.nextLong();

        System.out.print("Enter primitive root g: ");
        long g = sc.nextLong();

        System.out.print("Enter private key x: ");
        long x = sc.nextLong();

        long y = power(g, x, p);
        System.out.println("Public key y: " + y);

        System.out.print("Enter message m (< p): ");
        long m = sc.nextLong();

        if (m >= p) {
            System.out.println("Message must be less than p");
            return;
        }

        System.out.print("Enter random k: ");
        long k = sc.nextLong();

        long c1 = power(g, k, p);
        long c2 = (m * power(y, k, p)) % p;

        System.out.println("\nEncrypted:");
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);

        long s = power(c1, x, p);
        long s_inv = modInverse(s, p);

        if (s_inv == -1) {
            System.out.println("Inverse not possible");
            return;
        }

        long decrypted = (c2 * s_inv) % p;

        System.out.println("\nDecrypted message: " + decrypted);

        sc.close();
    }
}