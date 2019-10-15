import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StringAnimation {

	public static String txt4 = "The breeding colonies of gentoo penguins are located on ice-free surfaces. \r\n"
			+ "Colonies can be directly on the shoreline or can be located considerably inland. \r\n"
			+ "They prefer shallow coastal areas and often nest between tufts of grass. \r\n"
			+ "In South Georgia, for example, breeding colonies are 2 km inland. \r\n"
			+ "Whereas in colonies farther inland, where the penguins nest in grassy areas, \r\n"
			+ "they shift location slightly every year because the grass will become trampled over time.\r\n"
			+ "The gentoos' diet is high in salt as they eat organisms with relatively theea water, \r\n"
			+ "and this can lead to complications associated with high sodium concentrations o chicks. \r\n"
			+ "To counteract this, gentoos as well as many other marine bird species have a highly \r\n"
			+ "developed salt gland located above their eyes that takes the high concentrae body and produces \r\n"
			+ "a highly saline-concentrated solution that drips out of the body from the tip of the beak.";

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		PrintCrossoverTransition(GenerateScrambledString(txt4), txt4, 5,5);
		long finish = System.currentTimeMillis();
		System.err.print("elapsed :"+(finish - start)/1000f);
	}

	public static void PrintCrossoverTransition(String from, String to, int fromWait, int toWait) throws Exception {

		String[] fromLines = SplitString(from);
		String[] toLines = SplitString(to);

		if (from.length() != to.length() || fromLines.length != toLines.length) {
			throw new Exception("Strings must have the same length and same amount of lines!"+from.length()+" "+to.length()+" "+fromLines.length+" "+toLines.length);
		}

		for (int l = 0; l < fromLines.length; l++) {

			// Print 'From'-Line
			for (int i = 0; i < fromLines[l].length(); i++) {
				System.out.print(fromLines[l].charAt(i));
				TimeUnit.MICROSECONDS.sleep(fromWait);
			}

			// Reset Cursor
			System.out.print("\r");

			// Override Line with 'To'-Line
			for (int i = 0; i < toLines[l].length(); i++) {
				System.out.print(toLines[l].charAt(i));
				TimeUnit.MICROSECONDS.sleep(toWait);
			}

			// Go to next Line
			System.out.print("\n");
		}
	}

	public static String[] SplitString(String input) {
		String[] lines = input.split("\r\n|\r|\n");
		for (String s : lines) {
			s = s.replaceAll("\n", "");
			s = s.replaceAll("\r", "");
			s = s.replaceAll("\r\n", "");
		}
		return lines;
	}

	public static String GenerateScrambledString(String input) {

		String output = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '\n')
				output += '\n';
			else if(input.charAt(i) == '\r')
				output += '\r';
			else
				output += RandomChar();
		}
		return output;
	}

	public static char RandomChar() {
		Random rnd = new Random();
		return (char) (rnd.nextInt(89) + 33); // Random
	}

	public static void clearScreen() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}
