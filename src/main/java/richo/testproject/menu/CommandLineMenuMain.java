package richo.testproject.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineMenuMain
{
	public static void main(String[] args) throws Exception
	{
		CommandLineMenuMain main = new CommandLineMenuMain();
		main.go();
	}

	private void go() throws Exception
	{
		System.out.println("Hello\nSelectYourOption\n1. Option 1\n2. Option 2");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		System.out.println("You wrote [" + s + "]");
	}
}
