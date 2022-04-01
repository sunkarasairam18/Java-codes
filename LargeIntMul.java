public class LargeIntMul {
    public static void main(String[] args){
        long i = 55255L;
        long j = 57239L;
        String a = Long.toBinaryString(i);
        String b = Long.toBinaryString(j);
        System.out.println(i+" in Binary form "+a+" ("+a.length()+" bits)");
        System.out.println(j+" in Binary form "+b+" ("+b.length()+" bits)");
        String product = multiply(a,b);
        System.out.println("Product is "+Long.parseLong(product,2)+" in Binary form "+product+" ("+product.length()+"bits)");
    }
    static String multiply(String a,String b){
        String product = null;
        a = removeUnwanted(a);
        b = removeUnwanted(b);
        int al = a.length(),bl= b.length();
        int big = Math.max(al, bl);
        int i = 1;
        while(i<big){
            i *= 2;
        }
        a = addZerosBegin(a,i-al);
        b = addZerosBegin(b,i-bl);
        if(i == 1) product = singleBitMul(a,b);
        if(i>1){
            int first_half = i/2;
            int second_half = i - first_half;
            String wy = multiply(a.substring(0,first_half),b.substring(0,first_half));
            product = addZerosEnd(wy,i);
            String xz = multiply(a.substring(second_half),b.substring(second_half));
            String w_x = addBinary(a.substring(0,first_half),a.substring(second_half));
            String y_z = addBinary(b.substring(0,first_half),b.substring(second_half));
            w_x = multiply(w_x,y_z);
            w_x = subBinary(w_x,wy);
            w_x = subBinary(w_x,xz);
            w_x = addZerosEnd(w_x,first_half);
            product = addBinary(product,w_x);
            product = addBinary(product,xz);
        }
        return product;
    }
    private static String singleBitMul(String a,String b){
        if(a.equals("0") || b.equals("0")) return "0";
        return "1";
    }
    private static String addBinary(String a, String b)
    {
        a = removeUnwanted(a);
        b = removeUnwanted(b);
        String big = a.length()>b.length()?a:b;
        String small = a.length()>b.length()?b:a;
        int i = big.length() - 1;
        int j = small.length() - 1;
        StringBuilder re = new StringBuilder();
        String s;
        String carry = "0";
        while(i >= 0 || j >= 0){
            if(j>=0){
                s = decide(big.charAt(i)+"",small.charAt(j)+"",carry);
                j--;
            }
            else{
                s = decide(big.charAt(i)+"",carry);
            }
            if(s.length() == 1){
                re.insert(0, s);
                carry = "0";
            }
            if(s.length() == 2){
                re.insert(0, s.charAt(1));
                carry = "1";
            }
            i--;
        }
        if(carry.equals("1")) re.insert(0, "1");
        return re.toString();
    }
    private static String decide(String a,String b){
        return Integer.toBinaryString(Integer.parseInt(a,2)+Integer.parseInt(b,2));
    }
    private static String decide(String a,String b,String c){
        int i = Integer.parseInt(a,2) + Integer.parseInt(b,2) + Integer.parseInt(c,2);
        return Integer.toBinaryString(i);
    }
    private static String addZerosBegin(String s,int n){
        while(n>0){
            s = "0" + s;
            n--;
        }
        return s;
    }
    private static String addZerosEnd(String s,int n){
        while(n>0){
            s = s + "0";
            n--;
        }
        return s;
    }
    private static String subBinary(String a,String b){
        String big,small;
        a = removeUnwanted(a);
        b = removeUnwanted(b);
        if(a.length()>=b.length()){
            big = a;
            small = b;
        }
        else{
            big = b;
            small = a;
        }
        small = addZerosBegin(small,big.length()-small.length());
        String two_complement = TwoComplement(small);
        two_complement = addBinary(big,two_complement);
        if(two_complement.length() - big.length() == 1){
            two_complement = two_complement.substring(1);
        }
        return two_complement;
    }
    private static String TwoComplement(String a){
        StringBuilder b = new StringBuilder(a);
        int n = b.length();
        int i;
        for (i = n-1 ; i >= 0 ; i--)
            if (b.charAt(i) == '1')
                break;
        if (i == -1) return "1" + b;
        for (int k = i - 1 ; k >= 0; k--)
        {
            if (b.charAt(k) == '1')
                b.replace(k, k+1, "0");
            else
                b.replace(k, k+1, "1");
        }
        return b.toString();
    }
    private static String removeUnwanted(String a){
        int i = a.indexOf('1');
        if(i!=-1) return a.substring(i);
        return "0";
    }
}
