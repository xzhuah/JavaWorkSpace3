import java.util.HashMap;

import javax.imageio.ImageIO;
public class Random {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> a = getItemList("{\"item\":[{\"item_10_num\":0,\"item_5_num\":0,\"item_2_num\":0,\"item_13_num\":0,\"item_19_num\":0,\"item_4_num\":0,\"item_17_num\":0,\"item_8_num\":0,\"userID\":\"000002\",\"item_15_num\":0,\"item_6_num\":0,\"item_11_num\":0,\"item_1_num\":0,\"item_9_num\":0,\"item_18_num\":0,\"item_20_num\":0,\"item_3_num\":0,\"item_12_num\":0,\"item_14_num\":0,\"item_7_num\":0,\"item_16_num\":0}]}");
	    System.out.println(a);

	}
	public static HashMap<Integer, Integer> getItemList(String str) {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        int number=0, count = 0;
        for (int i = 0, j = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                for (j = i+1; j < str.length() ; j++) {
               // System.out.print(i+" "+j);
                if(str.charAt(j) == '_' ){
                	System.out.print(str.substring(i+1, j -1) + "  ");
                
                	number = Integer.parseInt(str.substring(i, j -1));
                
                
                i = j;
                }
            }
            if(i >= str.length())
            	break;
            if (str.charAt(i) == ':' && str.charAt(i + 1) != '\"' && str.charAt(i+1) != '[') {
                for (j = i+1;j < str.length()&& str.charAt(j) != ',' ; j++) ;
                System.out.println(str.substring(i+1, j -1));
               	count = Integer.parseInt(str.substring(i, j -1 ));
                i = j;
                result.put(number, count);
            }
        }
        return result;
    }
		return result;

}
}
