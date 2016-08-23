import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;



public class Snippet {
	public static void main(String[] args) {
		String[] strings = {"a", "b", "c"};
		String concat = Arrays.toString(strings);
		System.out.println(concat
				.replace("[", "(")
				.replaceAll("]", ")")
				);
		
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateInString = "2016-01-23";

        Date date;
		try {
			date = formatter.parse(dateInString);

            System.out.println(date);
            System.out.println(formatter.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}