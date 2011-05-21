package datastructure;

public class Btree
{
   protected RBtree left;
   protected RBtree right;
   protected int x;
   protected int y;           
   protected int info;    
   
   static RBtree sentinal;
   static RBtree root;
   
   public Btree(int e)                        
   {
	  left = sentinal;
          right = sentinal;  	
          info = e;                  
   }
   
   public int getx()
   {
        return x;
   }
   
    public int gety()
   {
        return y;
   }
    
   
}