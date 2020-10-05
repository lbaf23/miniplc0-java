import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        File f = new File(args[0]);
        String content = getFileContent(f);
        LexicalAnalysis l = new LexicalAnalysis(content);
        ArrayList<Lexi> res = l.getSym();

        for(Lexi i: res){
            System.out.println(i);
            if(i.toString().equals("Unknown"))
                break;
        }


/*
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        LexicalAnalysis l = new LexicalAnalysis(str);
        System.out.println(l.getSym());
        */

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
