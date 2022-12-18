package serveur;
	import java.io.BufferedInputStream;
	import java.io.BufferedOutputStream;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.io.OutputStreamWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.Scanner;
	public class Serveur{
	    public static void main(String[] args) {
	    Runnable r1=new Runnable(){
	        @Override
	        public void run(){
	       ServerSocket listener = null;
	       String line="";
	       BufferedReader is=null;
	       BufferedWriter os=null;
	       Socket socketOfServer = null;
	

	       // Try to open a server socket on port 9999
	       // Note that we can't choose a port less than 1023 if we are not
	       // privileged users (root)
	

	 
	       try {
	           listener = new ServerSocket(9999);
	       } catch (IOException e) {
	           System.out.println(e);
	           System.exit(1);
	       }
	

	       try {
	           System.out.println("Server is waiting to accept user...");
	

	           // Accept client connection request
	           // Get new Socket at Server.    
	           socketOfServer = listener.accept();
	           System.out.println("Mety ee");
	

	           // Open input and output streams
	           is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
	           //os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
	            Scanner s=new Scanner(System.in);
	

	           while (true) {
	               // Read data to the server (sent from client).
	                // line = is.readLine();
	                // System.out.print(line);
	               //line=s.next();
	               // Write to socket of Server
	               // (Send to client)
	               //os.write("anja " + line);
	               // End of line
	               //os.newLine();
	               // Flush data.
	               //os.flush();  
	

	                
	                int lu;
	                
	

	                //creation de l'entree
	                InputStream input=socketOfServer.getInputStream();
	                OutputStream out=new FileOutputStream("recu.txt");
	                //recoit du client 
	                BufferedInputStream inbuffer=new BufferedInputStream(input);
	                //envoi vers le fichier
	                BufferedOutputStream outbuffer=new BufferedOutputStream(out);
	                lu=inbuffer.read();
	

	                int compteur=0;
	                while(lu>-1){
	                    outbuffer.write(lu);
	                    lu=inbuffer.read();
	                    compteur++;
	                }
	                outbuffer.write(lu);
	                outbuffer.flush();
	                inbuffer.close();
	                out.flush();
	                out.close();
	                input.close();
	                socketOfServer.close();
	               // If users send QUIT (To end conversation).
	               if (line.equals("exit")) {
	                   os.write(">> OK");
	                   os.newLine();
	                   os.flush();
	                   break;
	               }
	           }
	

	       } catch (IOException e) {
	           System.out.println(e);
	           e.printStackTrace();
	       }
	       System.out.println("Nijanona");
	        }
	    };
	    Thread recever=new Thread(r1);
	    recever.start();
	

	}
	}
