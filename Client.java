package client;
	import java.io.*;
	import java.net.*;
	import java.util.Scanner;
	public class Client{
	    public static void main(String[] args) {
	    final String serverHost = "localhost";
	

	    Socket socketOfClient = null;
	    BufferedWriter os = null;
	    BufferedReader in=null;
	    
	    try
	    {
	

	        // Send a request to connect to the server is listening
	        // on machine 'localhost' port 9999.
	        socketOfClient = new Socket(serverHost, 9999);
	

	        // Create output stream at the client (to send data to the server)
	        os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
	        // in = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
	

	    }catch(UnknownHostException er)
	    {
	        System.err.println("Don't know about host " + serverHost);
	        return;
	    }catch(IOException es)
	    {
	        System.err.println("Couldn't get I/O for the connection to " + serverHost);
	        return;
	    }
	

	    try
	    {   
	

	        // Write data to the output stream of the Client Socket.
	        // os.write("HELLO");
	

	        // End of line
	        // os.newLine();
	

	        // Flush data.
	        // os.flush();
	        // os.write("I am Tom Cat");
	        // os.newLine();
	        // os.flush();
	        // os.write("QUIT");
	        // os.newLine();
	        // os.flush();
	

	        // Read data sent from the server.
	        // By reading the input stream of the Client Socket.
	        // os.write("nonnn");
	        // os.write("nonnn");
	        // os.newLine();
	        // os.write("QUIT");
	        // os.newLine();
	        // os.flush();
	        String responseLine="";
	

	        File f = new File("donnees.txt");
	        f.createNewFile();
	        f.setWritable(true);
	        PrintWriter writer = new PrintWriter(f);
	        writer.println("user.dir: " + System.getProperty("user.dir"));
	        writer.println("home: " + System.getProperty("user.home"));
	        writer.println("os.name: " + System.getProperty("os.name"));
	        writer.println("version: " + System.getProperty("java.runtime.version"));
	        writer.println("name: " + System.getProperty("user.name"));
            writer.println("RAM: "+ Runtime.getRuntime().totalMemory()+ "et " + Runtime.getRuntime().maxMemory());
	        writer.close();
	        // Scanner s = new Scanner( System.getProperty("os.name"));
	            while (true) {
	                if(f.exists()){
	                    long taille=f.length();
	                    // long pass=taille/4096;
	                    InputStream is=new BufferedInputStream(new FileInputStream(f));
	                    ByteArrayOutputStream tabbyte=new ByteArrayOutputStream();
	                    BufferedOutputStream tampon=new BufferedOutputStream(tabbyte);
	                    OutputStream sortie=socketOfClient.getOutputStream();
	

	                    int lu=is.read();
	                    int[] aecrire=new int[4096];
	                    int compteur=0;
	                    long rendu=0;
	                    //tant qu'on est pas a la in du fichier
	                    while(lu>-1){
	                        aecrire[compteur]=lu;
	                        lu=is.read();
	                        compteur++;
	                    //quand on remplit le tableau, on envoie un paquet de 4096 octets
	                        if(compteur==4096){
	                            compteur=0;
	                            rendu++;
	                    //on remplit le tampon
	                            for(int x=0;x<compteur;x++){
	                                tampon.write(aecrire[x]);
	                                
	                            }
	                            sortie.write(tabbyte.toByteArray());
	                            tabbyte.reset();
	

	                        }
	

	                    }
	                    //on envoie le dernier paquet
	                    for(int x=0;x<4096;x++){
	                        tampon.write(aecrire[x]);
	                        
	                    }
	                    tampon.flush();
	                    sortie.write(tabbyte.toByteArray());
	                    sortie.flush();
	                    is.close();
	                    tampon.close();
	                    
	                }
	                if (responseLine == "exit") {
	                    os.write("OK");
	                    os.flush();
	                    break;
	                }
	            }
	        os.close();
	        socketOfClient.close();
	    }catch(
	    UnknownHostException e)
	    {
	        System.err.println("Trying to connect to unknown host: " + e);
	    }catch(
	    IOException e)
	    {
	        System.err.println("IOException:  " + e);
	    }
	}
	

	    public Client() {
	    }
	}

