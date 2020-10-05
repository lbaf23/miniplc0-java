import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try{
            File f = new File(args[0]);
            String content = getFileContent(f);
            LexicalAnalysis l = new LexicalAnalysis(content);
            ArrayList<Lexi> res = l.getSym();

            for(Lexi i: res){
                System.out.println(i);
                if(i.toString().equals("Unknown"))
                    break;
            }
        } catch (Exception ignored){
        }

    }

    public static String getFileContent(File f){
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            StringBuilder content = new StringBuilder();
            String line;
            while((line = bf.readLine()) != null){
                content.append(line).append('\r').append('\n');
            }
            return content.toString();
        } catch (Exception e){
            return "";
        }
    }
}
