import java.util.Scanner;

public class Recursion1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println(f(input.nextInt(), input.nextInt()));
		input.close();
	}

	static int f(int m, int n) {
		if (m == n) {
			return m;
		} else if (m > n) {
			return f(n, m-n);
		} else if (m < n) {
			return f(n, m);
		}
		return -1;
	}
}