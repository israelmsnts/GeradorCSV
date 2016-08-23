package util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private final static Pattern p = Pattern.compile("_([a-zA-Z])");

	public static String capitalize(String nome) {
		if (nome.contains("_")) {
			Matcher m = p.matcher(nome);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, m.group(1).toUpperCase());
			}
			m.appendTail(sb);
			nome = sb.toString();
		}
		return nome;
	}

	public static String ClassName(String nome) {
		return nome.substring(0, 1).toUpperCase() + nome.substring(1);
	}

}
