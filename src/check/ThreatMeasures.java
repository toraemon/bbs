package check;

public class ThreatMeasures{
	public static String htmlspecialchars(String str){
		String text = new String(str);
		String[] escape = {"&", "<", ">", "\"", "\'", "\n", "\t"};
		String[] replace = {"&amp;", "&lt;", "&gt;", "&quot", "&#39;", "<br>", "&#x0009;"};
		for(int i=0;i<escape.length;i++){
			text = text.replace(escape[i], replace[i]);
		}
		return text;
	}
	public static String defaultchars(String pass){
		String text = new String(pass);
		String[] capture = {"&amp;", "&lt;", "&gt;", "&quot", "&#39;", "<br>", "&#x0009;"};
		String[] replace = {"&", "<", ">", "\"", "\'", "\n", "\t"};
		for(int i=0;i<capture.length;i++){
			text = text.replace(capture[i],	replace[i]);
		}
		return text;
	}
}