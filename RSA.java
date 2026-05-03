import java.util.Scanner;

public class RSA {

    // Function to calculate gcd
    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Function to find modular inverse (d)
    static int modInverse(int e, int phi) {
        for (int d = 1; d < phi; d++) {
            if ((d * e) % phi == 1)
                return d;
        }
        return -1;
    }

    // Modular exponentiation
    static long power(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % mod;

            base = (base * base) % mod;
            exp = exp / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== RSA Algorithm ===");

        // Step 1: Input primes
        System.out.print("Enter prime p: ");
        int p = sc.nextInt();

        System.out.print("Enter prime q: ");
        int q = sc.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        // Step 2: Choose e
        int e;
        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1)
                break;
        }

        // Step 3: Compute d
        int d = modInverse(e, phi);

        System.out.println("\nPublic Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        // Step 4: Encrypt
        System.out.print("\nEnter message (number): ");
        int msg = sc.nextInt();

        long cipher = power(msg, e, n);
        System.out.println("Encrypted Message: " + cipher);

        // Step 5: Decrypt
        long decrypted = power(cipher, d, n);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}
