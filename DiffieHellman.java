import java.util.Scanner;

public class DiffieHellman {

    // Fast modular exponentiation (efficient for large powers)
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

        System.out.println("=== Diffie-Hellman Key Exchange ===");

        // Public values
        System.out.print("Enter prime number (p): ");
        long p = sc.nextLong();

        System.out.print("Enter primitive root (g): ");
        long g = sc.nextLong();

        // Private keys
        System.out.print("Enter Alice private key (a): ");
        long a = sc.nextLong();

        System.out.print("Enter Bob private key (b): ");
        long b = sc.nextLong();

        // Compute public keys
        long A = power(g, a, p); // Alice public key
        long B = power(g, b, p); // Bob public key

        System.out.println("\nPublic Keys:");
        System.out.println("Alice sends: " + A);
        System.out.println("Bob sends: " + B);

        // Compute shared secret
        long keyAlice = power(B, a, p);
        long keyBob = power(A, b, p);

        System.out.println("\nShared Secret Key:");
        System.out.println("Computed by Alice: " + keyAlice);
        System.out.println("Computed by Bob: " + keyBob);

        sc.close();
    }
}
