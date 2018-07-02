//======================================================
// MMsieve - prime generator boolean

// Copyright [2017] [mgr inz. Marek Matusiak]

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//======================================================
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MMSieve {
	public static boolean[] s;

	// ----------------------chooser---------------------
	public static int nextPrime(int p, boolean s[]) {
		int i = p;
		do {
			i++;
		} while (!s[i]);
		return i;
	}

	// ------------------- end chooser --------------------
	// -----------------------generator----------------------------
	public static void generator(int p, int e, int n, boolean s[]) {
		int l = (e - p + 1);
		int start = e + 1;
		int end = (p) * l + nextPrime(p, s) - 1;
		if (end >= n || end < 0) {
			end = n;
		}
		for (int i = start; i <= end; i++) {
			s[i] = s[i - l];
		}
	}

	// -------------------end generator------------------------
	int e, p, n;

	// ---------------------cleaning---------------------------
	public static void cleaning(int p, int e, boolean s[]) {

		for (int i = e; i >= p; i--) {

			if (s[i]) {

				s[i * p] = false;
			}
		}
	}

	// ----------------end cleaning------------------
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter the maximum number ");
		int n = in.nextInt();
		in.close();
		int startTime = (int) System.currentTimeMillis();

		if (n >= 5) {

			s = new boolean[n + 1];
		} else {
			s = new boolean[6];
		}

		int e = 2;
		int p = 2;
		s[2] = true;
		s[3] = true;
		s[5] = true;
		do {

			generator(p, e, n, s);
			cleaning(p, e, s);
			e = p * (e - p + 1) + nextPrime(p, s) - 1;
			p = nextPrime(p, s);

		} while ((p * e <= n && p * e > 0));

		generator(p, e, n, s);

		do {
			cleaning(p, n / p, s);
			p = nextPrime(p, s);
		} while (p * p <= n);
		int estimatedTime;
		estimatedTime = (int) (System.currentTimeMillis() - startTime);
		System.out.println("Action Time = " + estimatedTime + " Quantity " + (n));
		// ----------------------------- RECODR TO FILE
		// --------------------------
		PrintWriter record = null;
		try {
			record = new PrintWriter("Prime.txt");
		} catch (FileNotFoundException w) {

			w.printStackTrace();
		}
		int k = 0;
		for (int i = 0; i <= n; i++) {

			if (s[i]) {

				if (s[i]) {

					k = k + 1;
					record.print(i + " ");
				}
				if (k % 25 == 0) {
					record.println();
				}
			}
		}
		record.close();

	}

}
