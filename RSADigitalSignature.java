import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;

public class RSADigitalSignature {

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int modInverse(int e, int phi) {
        for (int d = 1; d < phi; d++) {
            if ((d * e) % phi == 1)
                return d;
        }
        return -1;
    }

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

    // SHA-256 Hash function
    static BigInteger hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes());
        return new BigInteger(1, hashBytes);
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== RSA Digital Signature (With SHA-256) ===");

        System.out.print("Enter prime p: ");
        int p = sc.nextInt();

        System.out.print("Enter prime q: ");
        int q = sc.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e;
        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1)
                break;
        }

        int d = modInverse(e, phi);

        System.out.println("\nPublic Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        sc.nextLine();
        System.out.print("\nEnter message: ");
        String message = sc.nextLine();

        BigInteger hashValue = hash(message);
        System.out.println("Hash: " + hashValue);

        long hashNum = hashValue.mod(BigInteger.valueOf(n)).longValue();

        long signature = power(hashNum, d, n);
        System.out.println("Digital Signature: " + signature);

        long verified = power(signature, e, n);

        if (verified == hashNum)
            System.out.println("Signature VALID");
        else
            System.out.println("Signature INVALID");

        sc.close();
    }
}

