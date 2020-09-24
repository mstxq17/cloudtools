package cloudtools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class GithubTest {

	@Test
	public void Test() {
		System.out.println("Hello world!");
		Double code = Math.random();
		System.out.println(code);
	}

	@Test
	public void HttpTest() {
		try {
			Scanner s = new Scanner(new URL("http://114.mx").openStream(), "utf-8");
			String rspHtml = s.useDelimiter("\\A").next();
			// System.out.println(rspHtml);
			Pattern pattern = Pattern.compile("name=\"token\" value=\"(.*?)\"");
			Matcher m = pattern.matcher(rspHtml);
			String token = "";
			if (m.find()) {
				token = m.group(1);
				System.out.print(token);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void Test1() {
		int a = Integer.parseInt("1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "");
		System.out.println(map.get("a") == "");
		if
	}
}
