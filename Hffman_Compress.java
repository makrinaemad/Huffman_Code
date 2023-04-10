import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;
import java.io.*; 
public class Main {
	public static void main(String[] args) {
				String text = "geeksforgeeks";

		// divide string into charcters

		char[] ch = text.toCharArray();
		//System.out.println(ch);
		//System.out.println(ch);
		//diffrenent char
		ArrayList<Character> dif_ch = new ArrayList<Character>();
		for (int i = 0; i < ch.length; i++) {
			if (!(dif_ch.contains(ch[i]))) {
				dif_ch.add(ch[i]);
			}
		}
		//System.out.println(dif_ch);


		// probability of each value
		int[] prob= new int[dif_ch.size()];
		for (int i = 0; i < dif_ch.size(); i++) {
			char temp = dif_ch.get(i);
			for (int j = 0; j< ch.length; j++) {
				if (temp == ch[j]) {
					prob[i]++;
				}
			}
		}
		for(int l=0;l<prob.length;l++)
		{
		//System.out.println(dif_ch.get(l) + ":" +prob[l]);
		}
		PriorityQueue<node> q
            = new PriorityQueue<node>(ch.length, new MyComparator());
            for (int i = 0; i < dif_ch.size(); i++) {
           node hn = new node();
 
            hn.c = dif_ch.get(i);
         // System.out.println(hn.c);
            
            hn.data = prob[i];
            //System.out.println(hn.data);
            hn.left = null;
            hn.right = null;
 
            // add functions adds
            // the huffman node to the queue.
            q.add(hn);
        }
       /* for(int y=0;y<q.size();y++)
        {
            
            System.out.println(q.poll());
            //q.poll()
        }*/
                node root = null;
                while (q.size() !=1) {
 
            node a= q.poll();
 
            node b= q.poll();
            
            node r = new node();

            r.data = a.data + b.data;
            r.left = a;
            r.right = b;
            root = r;
            q.add(r);
        }
        huff h=new huff();
         //System.out.print(h.dic);
         
 
        // print the codes by traversing the tree
       h.code(root, "");
       h.printhash(text);
       System.out.println("");
       System.out.println("original size ="+(8*text.length()));
      int  p=0;
      int result=0;
      for(int i=0;i<dif_ch.size();i++)
      {
          p=prob[i];
          String s=h.dic.get(Character.toString(dif_ch.get(i)));
          result+=(p*s.length());
          
      }
      System.out.println("size after compression =" + result);
     //  int nbits=0;
     //h.compsize(text);
       
       
    }

}

class huff {
 
 public HashMap<String, String> dic = new HashMap<String, String>();  
    public void code(node root, String s)
    {
 
        if (root.left
                == null
            && root.right
                   == null
            && Character.isLetter(root.c)) {
                		         System.out.println(root.c + ":" + s);
                		
                	dic.put(String.valueOf(root.c), s);
                		//	System.out.print(dic);
                //	addtohash(String.valueOf(root.c),s);
 
            return;
        }
        code(root.left, s + "0");
        code(root.right, s + "1");
      //  System.out.print(dic);
    }
    public void printhash(String text)
    {
        	  for(int i=0;i<text.length();i++)
        	  System.out.print(dic.get(Character.toString(text.charAt(i))));
        	//dic.put(String.valueOf(s), ss);
     }
    /* public void compsize(String text)
     {
         int nbits=0;
         int prob=0;
         for (int i = 1; i< text.length(); i++)
        {
			nbits=(int)(Math.log(dic.get(Character.toString(text.charAt(i))))) / 
                     Math.log(2)+1);
           }
           prob=
           result
     }*/
}
class node {
 
    int data;
    char c;
 
    node left;
    node right;
}
 

class MyComparator implements Comparator<node> {
    public int compare(node one, node two)
    {
 
        return one.data - two.data;
    }
}