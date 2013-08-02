package br.com.praia.jampaxadrez.model;
/*
 * Created on 15/04/2005
 */

import java.io.*;

/**
 * @author Diego
 */

public abstract class EasyIn {
	
  static String s = new String();
  static byte[] b = new byte[512];
  static int bytesRead = 0;

  public static String getString()
  {
     boolean ok = false;
     while(!ok)
     {
        try
        {
           bytesRead = System.in.read(b);
           s = new String(b,0,bytesRead-1);
           s=s.trim();
           ok = true;
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
     }
	return s;
   }

   public static int getInt()   {
      int i = 0;
      boolean ok = false;
      while(!ok)
      {
         try
         {
             bytesRead = System.in.read(b);
             s = new String(b,0,bytesRead-1);
               i = Integer.parseInt(s.trim());
             ok = true;
         }
         catch(NumberFormatException e)
         {
            System.out.println("Apenas inteiros são válidos!");
         }
         catch(IOException e)
         {
             System.out.println(e.getMessage());
         }
     }
     return i;
   }
}