public class Lexi {
    private String token;
    private int symbol;
    private String symbolSign;

    public Lexi(String token, int symbol, String symbolSign){
        this.token = token;
        this.symbol = symbol;
        this.symbolSign = symbolSign;

        if(symbolSign.equals("Int")) {
            int num = Integer.parseInt(token);
            this.token = String.valueOf(num);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public String getSymbolSign() {
        return symbolSign;
    }

    public void setSymbolSign(String symbolSign) {
        this.symbolSign = symbolSign;
    }

    public String toString(){
        if(symbolSign.equals("Ident") || symbolSign.equals("Int"))
            return symbolSign + "(" + token + ")";
        return symbolSign;
    }
}
