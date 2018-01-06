// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import java.io.*;

import common.Params;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class ServerDummy extends AbstractServer
{
  //Class variables *************************************************

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 6654;



  //Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public ServerDummy(int port)
  {
    super(port);
    System.out.println("Tables:");
    TestDB.getInstance().printAllTables();
    //TestDB.getInstance().addRow("itamar", 100);
    //TestDB.getInstance().getBalanceOf("itamar");
  }


  //Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client){
	    System.out.println("dummy server got message:" + msg);
	    Params params = new Params(msg.toString());
	    try {
	    	if(params.getParam("action").equals("RoutineSubscription")){
	    		Params resp = Params.getEmptyInstance();
	    		resp.addParam("status", "OK");
	    		resp.addParam("subscriptionID", "123");
	    		client.sendToClient(resp.toString());
	    	}
	    	else if(params.getParam("action").equals("clientLeave")){
	    		System.out.println("clientLeave");
	    		Params resp = Params.getEmptyInstance();
	    		resp.addParam("status", "OK");
	    		resp.addParam("payAmount", "123142");
	    		client.sendToClient(resp.toString());
	    	}else if(params.getParam("action").equals("clientEnter")){
	    		System.out.println("clientEnter");
	    		Params resp = Params.getEmptyInstance();
	    		resp.addParam("status", "OK");
	    		resp.addParam("needsSubscriptionID", "Yes");
	    		client.sendToClient(resp.toString());
	    	}else if(params.getParam("action").equals("clientEnterWithSubscriptionID")){
	    		System.out.println("clientEnterWithSubscriptionID");
	    		Params resp = Params.getEmptyInstance();
	    		resp.addParam("status", "OK");

	    		client.sendToClient(resp.toString());
	    	}else if(params.getParam("action").equals("clientCancelOrder")){
	    		System.out.println("clientCancelOrder");
	    		Params resp = Params.getEmptyInstance();
	    		resp.addParam("status", "OK");
	    		resp.addParam("returnAmount", "42155");
	    		client.sendToClient(resp.toString());
	    	}else if(params.getParam("action").equals("clientContact")){
	    		System.out.println("clientContact");
	    		Params resp = Params.getEmptyInstance();
	    		resp.addParam("status", "OK");
	    		client.sendToClient(resp.toString());
	    	}else{
	    		client.sendToClient("{}");
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }


  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }

  //Class methods ***************************************************

  /**
   * This method is responsible for the creation of
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555
   *          if no argument is entered.
   */
  public static void main(String[] args)
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }

    ServerDummy sv = new ServerDummy(port);

    try
    {
      sv.listen(); //Start listening for connections
    }
    catch (Exception ex)
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
