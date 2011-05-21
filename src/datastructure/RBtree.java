package datastructure;

public class RBtree extends Btree
{
   public enum c{Red, Black,Green}
   private c color;
   private RBtree parent;
   
   
   RBtree(int e, RBtree p)
   {
                super(e);
		if(p!=null)				
		{
			parent = p;		
			color = c.Red;
                        if(p==sentinal)                 /*root*/
                        {
                            x = 500;
                            y = 120;
                        }                         
		}
		else					/*sentinal*/
		{
			parent = sentinal;
			color = c.Black;
		}
   }
   
   public RBtree getparent()
   {
        return parent;
   }
   
   public int getpx()
   {
        return parent.x;
   }
   
   public int getpy()
   {
        return parent.y;
   }
   
   RBtree Insert(int e)            
   {		
            RBtree p = sentinal, q = this;
				 
	    while(q!=sentinal)
	    {
    		   p = q;
                   if(e == p.info)
                        return null;	   
    		   if(e > p.info)
    			q = p.right;
    		   else
    			q = p.left;
            }
				 
		   if(e >= p.info)
			    q = p.Setright(e);
                   else
		            q = p.Setleft(e);  
                            
             InsertFixup(q);  
             CheckOverlap(q);
             CheckRootOverlap(root);
             return q;
   }  
   
   int getcolor()
   {
        if(color == c.Black)
               return 1;
        else if(color == c.Red)
                 return 2;
             else
                 return 3;        
   }
   
   void setcolor(int c1)
   {
        if(c1 == 1)
               color = c.Black;
        else if(c1 == 2)
               color = c.Red;
             else
               color = c.Green;
   }
   
   int getpcolor()
   {
       if(parent.color == c.Black)
               return 1;
        else if(parent.color == c.Red)
                 return 2;
             else
                 return 3; 
   }
   
   RBtree Setleft(int e)
   {
          if(left !=sentinal);
                // Invalid Insertion               // here 
	  else
                left = new RBtree(e, this);
                left.x = left.parent.x-50;
                left.y = left.parent.y+50;
                              
          return(left);
   }

   RBtree Setright(int e)
   {          
         if(right!=sentinal);
                // Invalid Insertion                // here
 	 else
                right = new RBtree(e, this);
                right.x = right.parent.x+50;
                right.y = right.parent.y+50;
          
          return(right);
   }
   
   void InsertFixup(RBtree z)
   {
		RBtree y = sentinal;
		
		if(this == z)				/* root node */
		{
			z.color = c.Black;
			return;
		}
		
		while(z!=root && (z.parent).color == c.Red)
		{
			if(z.parent == ((z.parent).parent).left)
			{
				y = ((z.parent).parent).right;
				if(y.color == c.Red)
				{
                        		(z.parent).color = c.Black;
                                        y.color = c.Black;
                                        ((z.parent).parent).color = c.Red;
                                        z = (z.parent).parent;
				}
				else
				{
					if(z == (z.parent).right)
					{
						z = z.parent;
						LeftRotate(z);	
					}
					
					(z.parent).color = c.Black;
                                        ((z.parent).parent).color = c.Red;
                                        RightRotate((z.parent).parent);
				}
			}
			else
			{
				y = ((z.parent).parent).left;
				if(y.color == c.Red)
				{
					(z.parent).color = c.Black;
                                        y.color = c.Black;
                                        ((z.parent).parent).color = c.Red;
                                        z = (z.parent).parent;
				}
				else
				{
					if(z == (z.parent).left)
					{
						z = z.parent;
						RightRotate(z);						
					}
					
					(z.parent).color = c.Black;                                        
					((z.parent).parent).color = c.Red;                                        
					LeftRotate((z.parent).parent);
				}
			}
		}		
		root.color = c.Black;                
   }
   
   void LeftRotate(RBtree x)
   {
		RBtree y = x.right;
		x.right = y.left;
		(y.left).parent = x;
		y.parent = x.parent;
		
		if(x.parent == sentinal)
			root = y;
		else
		{
			if(x == (x.parent).left)
				(x.parent).left = y;
			else
				(x.parent).right = y;
		}	
		
		y.left = x;
		x.parent = y;
                
                AdjustL(x);
                CheckOverlap(x);
   }		
   
   void ChangeNode(RBtree  x, RBtree y)
   {
        x = y;
        
   }
   
   void RightRotate(RBtree y)
   {
		RBtree x = y.left;
		y.left = x .right;
		(x.right).parent = y;           
                x.parent = y.parent;            
		
		if(y.parent == sentinal)
			root = x;//
		else
		{
			if(y == (y.parent).right)
				(y.parent).right = x;
			else
				(y.parent).left = x;//
		}	
		
		x.right = y;//
		y.parent = x;
                
                AdjustR(y);
                CheckOverlap(y);
   }
   
   RBtree Search(int e)          
   {
          RBtree pos = this;
          while(pos!=sentinal)
          {
        	if(pos.info == e)
                        return(pos);
                else
                {
                     if(e > pos.info )    					           
                           pos = pos.right;                         	      
        	     else		         
                           pos = pos.left;	
                }   				
    	   }          
           return pos;
    }
   
   void Intrav()
   {
          if(this!=sentinal)
          {
              left.Intrav();   
                    
                // Display Info and Color of Node                               // here                    
              right.Intrav();            
          }
   }

   RBtree Successor(RBtree z)
   {
	RBtree child = z.right; 
         
        while(child.left !=sentinal)
                child = child.left;   
        return(child);
   }
   
   void DeleteFixup(RBtree x)
   {
		RBtree w=sentinal;
		while(x!=root && x.color == c.Black)
		{
			if(x==(x.parent).left)
			{
				w = (x.parent).right;
				if(w.color == c.Red)
				{
					w.color = c.Black;
					(x.parent).color = c.Red;
					LeftRotate(x.parent);
					w = (x.parent).right;
				}
				
				if((w.left).color == c.Black && (w.right).color == c.Black)
				{
					w.color = c.Red;
					x = x.parent;
				}
				else
				{
					if((w.right).color == c.Black)
					{
						(w.left).color = c.Black;
						w.color = c.Red;
						RightRotate(w);
						w = (x.parent).right;
					}
					
					w.color = (x.parent).color;
					(x.parent).color = c.Black;
					(w.right).color = c.Black;
					LeftRotate(x.parent);
					x = root;					
				}
			}
			else
			{
				w = (x.parent).left;
				if(w.color == c.Red)
				{
					w.color = c.Black;
					(x.parent).color = c.Red;
					RightRotate(x.parent);
					w = (x.parent).left;
				}
				
				if((w.right).color == c.Black && (w.left).color == c.Black)
				{
					w.color = c.Red;
					x = x.parent;
				}
				else
				{
					if((w.left).color == c.Black)
					{
						(w.right).color = c.Black;
						w.color = c.Red;
						LeftRotate(w);
						w = (x.parent).left;
					}
					
					w.color = (x.parent).color;
					(x.parent).color = c.Black;
					(w.left).color = c.Black;
					RightRotate(x.parent);
					x = root;					
				}
			}
		}
		
		x.color = c.Black;
   }
   
   RBtree DeleteNode()
   {
		RBtree y=sentinal,x=sentinal;
		
		if(left==sentinal || right==sentinal)
				y = this;
		else	
				y = Successor(this);
		
		if(y.left != sentinal)
				x = y.left;
		else
				x = y.right;
		
                if(x!=sentinal)
                {
                    x.x = y.x;
                    x.y = y.y;
                }                
		x.parent = y.parent;
		
		if(y.parent == sentinal)
			root = x;
		else 
		{
			if( y == (y.parent).left)
				(y.parent).left = x;
			else
				(y.parent).right = x;				
		}		
		
		if(y != this)
			info = y.info;
		
		if(y.color == c.Black)
			DeleteFixup(x);			
		return y;
   }     

    public void AdjustR(RBtree n) 
    {
        n.parent.x += 50;
        n.parent.y -= 50;
        
        n.x += 50;
        n.y += 50;
        
        AdjustRightTraverseR(n.right);
        AdjustLeftTraverseR(n.parent.left);        
    }

    public void AdjustRightTraverseR(RBtree n) 
    {
        if(n!=sentinal)
        {
            AdjustRightTraverseR(n.left);
                n.x += 50;
                n.y += 50;
            AdjustRightTraverseR(n.right);
        }
    }

    public void AdjustLeftTraverseR(RBtree n) 
    {
        if(n!=sentinal)
        {
            AdjustLeftTraverseR(n.left);
                n.x += 50;
                n.y -= 50;
            AdjustLeftTraverseR(n.right);
        }
    }
    
      public void AdjustL(RBtree n) 
    {
        n.parent.x -= 50;
        n.parent.y -= 50;
        
        n.x -= 50;
        n.y += 50;
        
        AdjustLeftTraverseL(n.left);
        AdjustRightTraverseL(n.parent.right);        
    }

    public void AdjustRightTraverseL(RBtree n) 
    {
        if(n!=sentinal)
        {
            AdjustRightTraverseL(n.left);
                n.x -= 50;
                n.y -= 50;
            AdjustRightTraverseL(n.right);
        }
    }

    public void AdjustLeftTraverseL(RBtree n) 
    {
        if(n!=sentinal)
        {
            AdjustLeftTraverseL(n.left);
                n.x -= 50;
                n.y += 50;
            AdjustLeftTraverseL(n.right);
        }
    }

    public void CheckOverlap(RBtree n) 
    {
        if(n!=root)
        {
            if((n == n.parent.left && n.parent == n.parent.parent.right) || (n == n.parent.right && n.parent == n.parent.parent.left))
            {
                while(n!=root && (n.x >= n.parent.parent.x && n.x <=(n.parent.parent.x+25)))
                {
                    if(n.parent.x < n.x)
                    {   
                        if((n.parent.x-35) >= n.parent.parent.parent.x && (n.parent.x-35) <=(n.parent.parent.parent.x+25))
                        {
                            ShiftLeft(n.parent);
                            n = n.parent;
                        }                            
                        else
                        {
                            ShiftLeft(n.parent);
                            break;
                        }
                    }                                       
                    else
                    {   
                        if((n.parent.x+35) >= n.parent.parent.parent.x && (n.parent.x+35) <=(n.parent.parent.parent.x+25))
                        {
                            ShiftRight(n.parent);
                            n = n.parent;
                        }
                         else
                         {
                            ShiftRight(n.parent);
                            break;
                         } 
                    }                                                                                      

                }                                       
            }
            
            if(n.parent !=root)
            {
                if(n == n.parent.right && n.parent.parent == n.parent.parent.parent.left)
                   ShiftLeft(n.parent.parent);

                if(n == n.parent.left && n.parent.parent == n.parent.parent.parent.right)
                   ShiftRight(n.parent.parent);   
                
                n = n.parent;
            }       
                
            while(n.info > root.info && n.x <= (root.x+25))                    
                ShiftRight(root.right);                       

            while(n.info < root.info && n.x >= root.x)                     
                ShiftLeft(root.left);              
        }        
    }

    public void ShiftRight(RBtree n) 
    {
        if(n!=sentinal)
        {
            ShiftRight(n.left);
                n.x+=35;
            ShiftRight(n.right);                    
        }
    }

    public void ShiftLeft(RBtree n) 
    {
        if(n!=sentinal)
        {
            ShiftLeft(n.left);
                n.x-=35;
            ShiftLeft(n.right);                    
        }
    }

    public void CheckRootOverlap(RBtree n) 
    {
       if(n!=sentinal) 
       {
            CheckRootOverlap(n.left);
            
                while(n.info > root.info && n.x <= (root.x+25))                    
                ShiftRight(root.right);                       

                while(n.info < root.info && n.x >= root.x)                     
                ShiftLeft(root.left);  
            
            CheckRootOverlap(n.right);
       }
    }

   
}