import java.util.ArrayList;
import java.util.HashMap;


public class LexicalAnalysis {
    public final static int Ident = 8;  // 内部字符串表示
    public final static int Int = 9;  // 二进制数值表示
    public final static int Colon = 10;
    public final static int Plus = 11;
    public final static int Star = 12;
    public final static int Comma = 13;
    public final static int LParenthesis = 14;
    public final static int RParenthesis = 15;
    public final static int Assign = 16;



    public final static HashMap<String, Integer> RESERVER = new HashMap<>();
    static {
        RESERVER.put("BEGIN",1);
        RESERVER.put("END",2);
        RESERVER.put("FOR",3);
        RESERVER.put("DO",4);
        RESERVER.put("IF",5);
        RESERVER.put("THEN",6);
        RESERVER.put("ELSE",7);
    }

    private String lexi;
    private int i;
    public ArrayList<Lexi> result = new ArrayList<>();
    public LexicalAnalysis(String lexi){
        this.lexi = lexi;
        this.i=0;
    }

    public String getLexi() {
        return lexi;
    }
    public void setLexi(String lexi) {
        this.lexi = lexi;
    }

    private boolean isSpace(){
        try{
            return now()==' ';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isNewline(){
        try{
            return now()=='\n' || now()=='\r';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isEndString(){
        try{
            return now()=='\0';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isTab(){
        try{
            return now()=='\t';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isLetter(){
        try{
            return (now()>='a' && now()<='z') || (now()>='A' && now()<='Z');
        } catch (Exception e){
            return false;
        }
    }
    private boolean isDigit(){
        try{
            return now()>='0' && now()<='9';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isEqu(){
        try{
            return now()=='=';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isColon(){
        try{
            return now()==':';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isPlus(){
        try{
            return now()=='+';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isMinus(){
        try{
            return now()=='-';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isStar(){
        try{
            return now()=='*';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isLeftPar(){
        try{
            return now()=='(';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isRightPar(){
        try{
            return now()==')';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isComma(){
        try{
            return now()==',';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isSemi(){
        try{
            return now()==';';
        } catch (Exception e){
            return false;
        }
    }
    private boolean isDivision(){
        try{
            return now()=='/';
        } catch (Exception e){
            return false;
        }
    }

    // 是否是保留字，不是则返回0
    public int isReserve(String word){
        try {
            return RESERVER.get(word);
        } catch (NullPointerException e){
            return 0;
        }
    }

    private void forward(){
        i++;
    }
    private char now() throws Exception{
        return lexi.charAt(i);
    }
    private void backward(){
        i--;
    }
    public boolean end(){
        try {
            now();
            return false;
        } catch (Exception e){
            return true;
        }
    }


    public ArrayList<Lexi> getSym(){
        StringBuilder token;

        while (!end()) {
            int symbol = 0;
            String symbolSign = "Unknown";

            token = new StringBuilder("");
            while(isSpace() || isNewline() || isTab() || isEndString()){
                forward();
            }

            if(isLetter()){
                while (isLetter() || isDigit()){
                    try{
                        token.append(now());
                    } catch (Exception ignored){}

                    forward();
                }
                backward();
                int resVal = isReserve(token.toString());
                if(resVal==0) {
                    symbol = Ident;
                    symbolSign = "Ident";
                }
                else {
                    symbol = resVal;
                    symbolSign = token.charAt(0) + token.substring(1).toLowerCase();
                }
            }
            else if(isDigit()){
                while (isDigit()){
                    try{
                        token.append(now());
                    } catch (Exception ignored){}
                    forward();
                }
                backward();
                symbol = Int;
                symbolSign = "Int";
            }
            else if(isColon()){
                forward();
                if(isEqu()){
                    symbol = Assign;
                    symbolSign = "Assign";
                    token.append(":=");
                }
                else {
                    backward();
                    symbol = Colon;
                    symbolSign = "Colon";
                    token.append(":");
                }
            }
            else if(isPlus()) {
                symbol = Plus;
                symbolSign = "Plus";
                token.append('+');
            }
            else if(isStar()) {
                symbol = Star;
                symbolSign = "Star";
                token.append('*');
            }
            else if(isLeftPar()) {
                symbol = LParenthesis;
                symbolSign = "LParenthesis";
                token.append('(');
            }
            else if(isRightPar()) {
                symbol = RParenthesis;
                symbolSign = "RParenthesis";
                token.append(')');
            }
            else if(isComma()) {
                symbol = Comma;
                symbolSign = "Comma";
                token.append(',');
            }
            else
                try {
                    token.append(now());
                } catch (Exception ignored){}

            if(!token.toString().equals(""))
                result.add(new Lexi(token.toString(), symbol, symbolSign));

            forward();
        }
        return result;
    }
}
