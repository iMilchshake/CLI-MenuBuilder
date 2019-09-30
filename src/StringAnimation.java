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

		String txt2 = "TeamViewer:\r\n" + "id: sickUSerNamexD23\r\n" + "pw: /ah38ah38Ha3\r\n" + "\r\n" + "Skyp3\r\n"
				+ "user: oldmcdonnald\r\n" + "pw: hatnefarm123\r\n" + "\r\n" + "Teamspeak5xd\r\n"
				+ "lel: ich bin ein baum\r\n" + "user: helo\r\n" + "pw: existerit nicht????";

		String txt = "Ode to the Chimpanzee\n" + "A Sonnet by yeet\n" + "My chimpanzee, you inspire me to write.\n"
				+ "I love the way you cuddle, prowl and chirp,\n" + "Invading my mind day and through the night,\n"
				+ "Always dreaming about the intense hjerpe.\n" + "\n" + "Let me compare you to a furry moon?\n"
				+ "You are more chemic, nocturnal and plump.\n" + "Calm sun heats the blurry peaches of June,\n"
				+ "And summertime has the menacing jump.\n" + "\n" + "How do I love you? Let me count the ways.\n"
				+ "I love your dense elbows, lip and elbows.\n" + "Thinking of your vernal lip fills my days.\n"
				+ "My love for you is the tense pantyhose.\n" + "\n" + "Now I must away with a hirsute heart,\n"
				+ "Remember my keen words whilst we're apart.";

		long start = System.currentTimeMillis();
		PrintCrossoverTransition(GenerateScrambledString(txt4), txt4, 0.5f, 1f);
		long finish = System.currentTimeMillis();
		System.err.print("elapsed :"+(finish - start)/1000f);
		String[] tmp = SplitString(txt);

//		for (String s : tmp) {
//			// System.out.print(s);
//
//			for (int i = 0; i < s.length(); i++) {
//				System.out.print(RandomChar());
//				TimeUnit.MILLISECONDS.sleep(1);
//			}
//
//			System.out.print("\r"); // reset cursor
//
//			for (int i = 0; i < s.length(); i++) {
//				System.out.print(s.charAt(i));
//				TimeUnit.MILLISECONDS.sleep(4);
//			}
//
//			System.out.print("\n");
//		}
//			TimeUnit.MILLISECONDS.sleep(100);
	}

	public static void PrintCrossoverTransition(String from, String to, float time, float ratio) throws Exception {

		String[] fromLines = SplitString(from);
		String[] toLines = SplitString(to);

		if (from.length() != to.length() || fromLines.length != toLines.length) {
			throw new Exception("Strings must have the same length and same amount of lines!");
		}

		int fromWait = (int) ((time / (2 * from.length())) * ratio * 1000000f);
		int toWait = (int) (-1 * (time / (2 * to.length())) * (ratio - 2f) * 1000000f);

		System.err.println((float)fromWait*2*from.length()/1000000f);
		
		System.out.println(from.length() + " - " + to.length());
		System.out.println(fromWait + " - " + toWait);

		// for (String s : fromLines) {
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
		}
		return lines;
	}

	public static String GenerateScrambledString(String input) {

		String output = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '\n')
				output += '\n';
			else
				output += RandomChar();
		}
		return output;
	}

	public static char RandomChar() {
		Random rnd = new Random();
		return (char) (rnd.nextInt(89) + 33); //Random
	}

	public static void clearScreen() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}
}
