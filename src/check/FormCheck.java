package check;

import java.util.Date;
import java.text.SimpleDateFormat;

public class FormCheck {
	/* コメントフォームチェック */
	public static String commentFormatCheck(String comment){
		String check = "";
		comment = comment.replaceAll("　"," ");
		if(comment.matches("\\s{1,}") || comment.length()==0){
			check += "コメントは必ず何か入力してください。<br>";
		}
		if(!(comment.length()<=500)){
			check += "投稿できるコメントの文字数500文字を越えています。<br>";
		}
		return check;
	}
	/* 新規投稿フォームチェック */
	public static String contributionFormCheck(String title, String text, String date){
		String check = "";
		if(!(0<title.length() && title.length()<=50)){
			check += "タイトルは50文字以下で必ず入力してください。<br>";
		}
		if(title.matches("\\s{1,}") || title.trim().matches("　{1,}")){
			check += "タイトルは必ず何か文字を入力してください。<br>";
		}
		if(!(0<text.length() && text.length()<=1000)){
			check += "本文は1000文字以下で必ず入力してください。<br>";
		}
		if(text.matches("\\s{1,}") || text.trim().matches("　{1,}")){
			check += "本文は必ず何か文字を入力してください。<br>";
		}
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String today = sdf.format(d);
		if(today.compareTo(date)>0){
			check += "過去には投稿できません。<br>";
		}
		return check;
	}
	/* アカウント登録フォームチェック */
	public static String registAccountCheck(String accountId, String pass, String passCheck, String name){
		String check = "";
		if(!accountId.matches("[a-zA-Z0-9]{6,20}")){
			check += "アカウントIDは半角英数字で6から20文字で入力してください。<br>";
		}
		// 文字数判定のためエスケープした文字を一時的に戻す
		pass = ThreatMeasures.defaultchars(pass);
		if(!(pass.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}") && (6<=pass.length()&&pass.length()<=255))){
			check += "パスワードは全半角記号で6から255文字で入力してください。<br>";
		}
		pass = ThreatMeasures.htmlspecialchars(pass);
		if(!(pass.equals(passCheck))){
			check += "パスワードが一致しません。<br>";
		}
		name = name.replaceAll("　"," ");
		if(!(0<name.length() && name.length()<=10) || name.matches("\\s{1,}")){
			check += "アカウント名は10文字以下で必ず入力してください。<br>";
		}
		return check;
	}
	/* アカウント編集フォームチェック */
	public static String editAccountCheck(String accountId, String pass, String passCheck, String name){
		String check = "";
		if(!accountId.matches("[a-zA-Z0-9]{6,20}")){
			check += "アカウントIDは半角英数字で6から20文字で入力してください。<br>";
		}
		pass = ThreatMeasures.defaultchars(pass);
		if(!(pass.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}") && (6<=pass.length()&&pass.length()<=255) || pass.length()==0)){
			check += "パスワードは全半角記号で6から255文字で入力してください。<br>";
		}
		pass = ThreatMeasures.htmlspecialchars(pass);
		if(!(pass.equals(passCheck))){
			check += "パスワードが一致しません。<br>";
		}
		if(!(0<name.length() && name.length()<=10)){
			check += "名前は10文字以下で必ず入力してください。<br>";
		}
		return check;
	}
}
