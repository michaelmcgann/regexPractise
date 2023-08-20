import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranscriptParser {
    public static void main(String[] args) {
        String transcript = """
                Student Number:	1234598872			Grade:		11
                Birthdate:		01/02/2000			Gender:	M
                State ID:		8923827123
                                
                Cumulative GPA (Weighted)		3.82
                Cumulative GPA (Unweighted)	3.46
                """;

        String regex = """
                Student\\sNumber:\\s(?<studentNum>\\d{10}) # Gets student number
                \\s+Grade:\\s+(?<grade>\\d{1,2}).* # Gets student grade
                Birthdate:\\s+(?<birthMonth>\\d{2})/(?<birthDay>\\d{2})/(?<birthYear>\\d{4}).* # Gets DOB
                \\s+Gender:\\s(?<gender>\\w).* # gets gender
                State\\sID:\\s+(?<stateId>\\d{10}).* # Gets State ID
                Cumulative\\sGPA\\s\\(Weighted\\)\\s+(?<weightedGpa>\\d\\.\\d{1,2}).*
                Cumulative\\sGPA\\s\\(Unweighted\\)\\s(?<unweightedGpa>\\d\\.\\d{1,2}).*
                """;
        Pattern pat = Pattern.compile(regex, Pattern.DOTALL | Pattern.COMMENTS);
        Matcher mat = pat.matcher(transcript);

        if (mat.matches()) {
            System.out.format("Student Number: %s\n", mat.group("studentNum"));
            System.out.format("Grade: %s\n", mat.group("grade"));
            System.out.format("Birth Month: %s\n", mat.group("birthMonth"));
            System.out.format("Birth Day of Month: %s\n", mat.group("birthDay"));
            System.out.format("Birth Year: %s\n", mat.group("birthYear"));
            System.out.format("Gender: %s\n", mat.group("gender"));
            System.out.format("State ID: %s\n", mat.group("stateId"));
            System.out.format("Weighted GPA: %s\n", mat.group("weightedGpa"));
            System.out.format("unweighted GPA: %s\n", mat.group("unweightedGpa"));
        }
    }
}
