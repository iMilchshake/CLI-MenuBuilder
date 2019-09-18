import jfiglet.FigletFont;
import sun.security.action.GetBooleanSecurityPropertyAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

//Java Figlet(jfiglet) by lalyos https://github.com/lalyos/jfiglet

public class MenuBuilder {

	public static void main(String[] args) throws Exception {
		
		String[] subMenuEntries = {"[1] Start Script","[2] Options","[3] Exit"};
		System.out.println(BuildMenu("Java MenuBuilder",subMenuEntries, "standard.flf",1,2,1,1,0));

	}

	public static String BuildMenu(String Text, String[] menuEntries, String fontname, int borderthickness, int borderGapBannerX, int borderGapBannerY, int borderGapMenuX, int borderGapMenuY) throws Exception {

		// SETTINGS:
		char[] border_symbs = { '─', '│', '┌', '┐', '└', '┘', '├', '┤', '┬', '┴', '┼', ' ' };
		int border = borderthickness; // bordersize - dont increase me pls
		int[] emptyspace = { borderGapBannerX, borderGapBannerY }; // x,y
		int[] subMenuEmptySpace = { borderGapMenuX, borderGapMenuY };
		String[] subMenuEntries = menuEntries;
		
//		int subMenuSpaceBetweenLines = 2; //not implemented yet 
		String maintext = FigletFont.convertOneLine("classpath:/" + fontname, Text); // Create Figlet Text
		maintext = maintext.replaceAll("[\n\r]+", "\n"); // GET RID OF EMPTY LINES 1
		maintext = maintext.replaceAll("\n[ \t]*\n", "\n"); // GET RID OF EMPTY LINES 2

		int rows = 0, maxCollumns = 0, currentLength = 0;
		for (int i = 0; i < maintext.length(); i++) {
			currentLength++;
			if (maintext.charAt(i) == '\n') // Found Linebreak
			{
				rows++;
				if (currentLength - 1 > maxCollumns)
					maxCollumns = currentLength - 1;
				currentLength = 0;
			}
		}
		
		// Get Longest SubMenu Entry
		int subMenuTextWidth = 0;
		for (String s : subMenuEntries)
			if (s.length() > subMenuTextWidth)
				subMenuTextWidth = s.length();

		subMenuTextWidth -= 1; // TEMPORY SOLUTION, PLS FIX ME!

		String subMenuString = "";
		for (int i = 0; i < subMenuEntries.length; i++) {
			for (int c = 0; c <= subMenuTextWidth; c++) {
				if (c < subMenuEntries[i].length())
					subMenuString += subMenuEntries[i].charAt(c);
				else
					subMenuString += ' ';
			}

			for (int a = 0; a <= subMenuTextWidth; a++)
				subMenuString += ' ';

		}

		// Calculate overall Size of Grid
		int gridWidth = maxCollumns + border * 2 + emptyspace[0] * 2;
		int bannerHeight = rows + border * 2 + emptyspace[1] * 2;
		int gridHeight = bannerHeight + Math.max((2 * subMenuEntries.length) + subMenuEmptySpace[1] * 2, 0);

		int[][] grid = new int[gridWidth][gridHeight]; // 0 empty, 1 border, 2 banner-text, 3 submenu-text [Y,X]

		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (y < bannerHeight) { // BANNER
					if (x < border || y < border || y >= bannerHeight - border || x >= gridWidth - border)
						grid[x][y] = 1;
					else if (x < border + emptyspace[0] || y < border + emptyspace[1]
							|| y >= bannerHeight - border - emptyspace[1] || x >= gridWidth - border - emptyspace[0])
						grid[x][y] = 0;
					else
						grid[x][y] = 2;
				} else { // SUB MENU AREA
					if (x < border
							|| (y >= gridHeight - border
									&& x < subMenuTextWidth + border * 2 + subMenuEmptySpace[0] * 2)
							|| x == subMenuTextWidth + border * 2 + subMenuEmptySpace[0] * 2)
						grid[x][y] = 1;
					else if (x < border + subMenuEmptySpace[0] || (y < bannerHeight + subMenuEmptySpace[1])
							|| x >= subMenuTextWidth + border * 2 + subMenuEmptySpace[0]
							|| (y >= gridHeight - subMenuEmptySpace[1] - border))
						grid[x][y] = 0;
					else if (x < subMenuTextWidth + border * 2 + subMenuEmptySpace[0] * 2)
						grid[x][y] = 3;
					else
						grid[x][y] = 0;
				}
			}
		}

		int bannerindex = 0;
		int submenuindex = 0;
		String output = "";
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (grid[x][y] == 0)
					output += ' ';
				else if (grid[x][y] == 1) {
					boolean left = false, right = false, up = false, down = false;

					if (x > 0)
						left = (grid[x - 1][y] == 1);
					if (y > 0)
						up = (grid[x][y - 1] == 1);
					if (x < gridWidth - 1)
						right = (grid[x + 1][y] == 1);
					if (y < gridHeight - 1)
						down = (grid[x][y + 1] == 1);

					output += border_symbs[getSymbNumber(up, right, down, left)];
				} else if (grid[x][y] == 2) {
					Character c = maintext.charAt(bannerindex++);
					if (c == '\n')
						c = maintext.charAt(bannerindex++);

					output += c;
				} else if (grid[x][y] == 3)
					output += subMenuString.charAt(submenuindex++);
			}
			output += "\n";
		}
		return output;
	}

	private static int getSymbNumber(boolean up, boolean right, boolean down, boolean left) {

		if (!up && right && !down && left)
			return 0;
		else if (up && !right && down && !left)
			return 1;
		else if (!up && right && down && !left)
			return 2;
		else if (!up && !right && down && left)
			return 3;
		else if (up && right && !down && !left)
			return 4;
		else if (up && !right && !down && left)
			return 5;
		else if (up && right && down && !left)
			return 6;
		else if (up && !right && down && left)
			return 7;
		else if (!up && right && down && left)
			return 8;
		else if (up && right && !down && left)
			return 9;
		else if (up && right && down && left)
			return 10;

		return 11;
	}

}
