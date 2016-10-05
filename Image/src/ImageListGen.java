
public class ImageListGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for(int i=1;i<13;i++){
//			for(int j=1;j<8;j++){
//				System.out.println("<ImageButton android:id=\"@+id/item_"+i+""+j+"\" android:layout_width=\"50dp\" android:layout_height=\"50dp\" android:background=\"@drawable/lock\"/>");
//			}
//		}
//		
//		for(int i=1;i<13;i++){
//			for(int j=1;j<8;j++){
//				System.out.println("buttons["+(i-1)+"]["+(j-1)+"]=(ImageButton)dialog.findViewById(R.id.item_"+i+""+j+");");
//			}
//		}
//		for(int i=1;i<13;i++){
//			for(int j=1;j<8;j++){
//				System.out.println("buttons["+(i-1)+"]["+(j-1)+"].setTag("+((i-1)*7+(j-1))+");");
//			}
//		}
//		for(int i=0;i<100;i++){
//			System.out.println(" <LinearLayout android:layout_width=\"match_parent\" android:layout_height=\"wrap_content\"><ImageView android:id=\"@+id/bag_image"+i+"\" android:layout_height=\"80dp\" android:layout_width=\"80dp\" android:background=\"@drawable/lock\"/><TextView android:id=\"@+id/bag_name"+i+"\" android:layout_width=\"150dp\" android:layout_height=\"80dp\" android:textSize=\"20dp\" android:gravity=\"center\" android:text=\"@string/item_unknown\"/><TextView android:id=\"@+id/bag_des"+i+"\" android:layout_width=\"wrap_content\" android:layout_height=\"80dp\" android:textSize=\"20dp\" android:gravity=\"center\" android:text=\"@string/item_unknown_material\"/></LinearLayout>");
//		}
//		for(int i=0;i<100;i++){
//		System.out.println("allImage["+i+"]=(ImageView)findViewById(R.id.bag_image"+i+");");
//		System.out.println("allName["+i+"]=(TextView)findViewById(R.id.bag_name"+i+");");
//	}

		for(int i=0;i<100;i++){
			System.out.println("allNum["+i+"]=(TextView)findViewById(R.id.bag_des"+i+");");
		}
	}

}
