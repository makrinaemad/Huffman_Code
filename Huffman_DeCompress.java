//package testt;

public class Main {
	//huffman decode 
	
		public static void main(String[] args) {
			String text="11111010001000001000111";//aabbcdefa
			HuffmanTree t =new 	HuffmanTree();
			t.AddItem('a',"11");
			t.AddItem('b',"10");
			t.AddItem('c',"001");
			t.AddItem('d',"0000");
			t.AddItem('e',"01");
			t.AddItem('f',"0001");
			System.out.println(t.Decoding(text));
			System.out.println("Compression size= " +t.getCompressSize());
			System.out.println("Decompression size= "+t.getDecompressSize());
			
		}
	}
	 class HuffmanTree  {

	    public Node root;
	public int compress_size=0;
	public int decompress_size=0;
	    public HuffmanTree(){
	        this.root = new Node();
	    }

	    public void AddItem(char c, String code){

	        Node node = this.root;
	 
	        for(int i=0;i<code.length();i++){
	        	 if(i==(code.length()-1)) {
	                 if(code.charAt(i)=='0'){

	                     node.left = new Node(c); 
	                   //  System.out.println( "left  "+node.left.getData());
	                    }
	                 else{
	                     node.right = new Node(c); 
	                    // System.out.println( "right    "+node.right.getData());
	                 }}
	        	 else if(code.charAt(i)=='0'){
	                if(node.left == null){
	                    node.left = new Node();
	                   node= node.left;
	                }
	                else{
	                   node = (Node) node.left;
	                }
	            }
	            else
	              if(code.charAt(i)=='1'){
	                if(node.right == null){
	                    node.right = new Node();
	                    node = node.right;
	                }
	                else{
	                    node = (Node) node.right;
	                }
	         }     
	        }
	      
	        }

	    public String Decoding(String huff_code){

	        String decode = "";
	        Node node = this.root;
	        for(int i= 0;i<huff_code.length();i++){

	            if(huff_code.charAt(i) == '0'){
	                node = node.left;

	                if(node.left == null && node.right == null){
	                    decode+= node.getData();
	                    node = this.root;
	                    decompress_size++;
	                }
	            }
	            else
	            {
	                node = node.right;
	                if(node.left == null && node.right == null){
	                    decode+= node.getData();
	                    node= this.root;
	                    decompress_size++;
	                }

	            }
	        }
	        compress_size=huff_code.length();
	        return decode;
	    }
	   
	    public int getDecompressSize() {
	    	return (8*decompress_size);
	    }
	    public int getCompressSize() {
	    	return compress_size;
	    }
	    }

	class Node{

	    Node left;
	    Node right;
	    char data;

	    public Node(){

	    }
	    public Node(char data){
	        this.data = data;
	    }
	    public void setData(char data){
	        this.data = data;
	    }
	    public char getData( ) {
	    	char data=this.data;
	        return data;
	    }
	}

