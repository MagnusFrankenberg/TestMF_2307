package magnus.Calculator_Problem3;

public class InputValidation {
    private boolean isTest;

    public InputValidation(boolean isTest){
        this.isTest = isTest;
    }


    public char[] removeWhiteSpace(String s){
        char[] noWhite = s.replaceAll("\\s","").toCharArray();
        return noWhite;
    }


    //Valid characters
    public boolean areValidChars(char[] chars){
        for(int i = 0; i< chars.length;i++) {
            char c = chars[i];
            if (!((c >= 48 && c <= 57) || c == '+' || c == '-' || c == '*' || c == '/' || c == '.')) {
                System.out.println(c + " = not valid character.\n");
                return false;
            }
        }
        return true;
    }



    //Tillåten plats på operator eller decimaltecken
    public boolean validStartEnd(char[] chars){
        char c0 = chars[0];
        char c1 = chars[1];
        char ce = chars[chars.length-1];
        if ((c0 == '+' || c0 == '*' || c0 == '/' || c0 == '.') ||
                (c0 == '-' && (c1 == '+' || c1 == '*' || c1 == '/' || c1 == '.')) ||
                (ce == '-' || ce == '+' || ce == '*' || ce == '/' || ce == '.')) {
            System.out.println("Non Valid expression");
            return false;
        }
        return true;
    }


    //operator inkluderad
    public boolean operatorIncluded(char[] chars){
        boolean included=false;
        if(chars[0]=='-'){
            for (int i = 2; i < chars.length-1; i++) {
                if(chars[i]=='-'||chars[i]=='+'||chars[i]=='*'||chars[i]=='/'){
                    included=true;
                }
            }
        }else{
            for(int i = 1; i < chars.length-1; i++){
                if(chars[i]=='-'||chars[i]=='+'||chars[i]=='*'||chars[i]=='/'){
                    included=true;
                }
            }
        }
        return included;
    }


    public boolean maxOneOperator(char[] chars){
        int countO = 0, countM = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='+'||chars[i]=='*'||chars[i]=='/'){
                countO++;
            }
            if(chars[i]=='-'){
                countM++;
            }
        }
        String s = new String(chars);
        if (countO > 1 || (chars[0] == '-' && countM > 3) || (chars[0] != '-' && countM > 2) ||
                (s.indexOf("-+") != -1) || (s.indexOf("-*") != -1) || (s.indexOf("-/") != -1)) {
            System.out.println("Only one operator allowed");
            return false;
        }
        return true;
    }


    public boolean maxOneDecimal(char[] chars){
        String s = new String(chars);
        if(s.indexOf("..") !=-1){
            System.out.println("Not valid input");
            return false;
        }
        return true;
    }


    public boolean isValidInput(char[] chars){
        return areValidChars(chars)&&
                validStartEnd(chars)&&
                operatorIncluded(chars)&&
                maxOneOperator(chars)&&
                maxOneDecimal(chars);
    }



}
