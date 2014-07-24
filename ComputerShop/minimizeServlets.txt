		
		String action = request.getParameter("action");
				
		try {
					
				InetAddress addr = InetAddress.getByName(hostname);
				Socket sock = new Socket(addr, TCP_PORT);
			
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
							
				requestMessage.setCommand(action);			
				out.writeObject(requestMessage);
				
				CommandMessage responseMessage = (CommandMessage) in.readObject();
				String retVal = responseMessage.getCommand();
			
				in.close();
				out.close();
				sock.close();
												
				session.setAttribute(action, responseMessage.getObject());    // kastovati kasnije 
			
				RequestDispatcher disp = getServletContext().getRequestDispatcher("/" + action + ".jsp");
				disp.forward(request, response);
				return;
			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	






/*
		Potrebno za castovanje u jsp-u (posto servlet prosledjuje listu OBJEKATA)  :
 <%
if (request != null) `enter code here`
         {
String gross = request.getParameter("gross");   //GROSS PARAM
if(gross!=null)
{
    int grossSal = Integer.parseInt(gross);          //Type-Casting
}
double netSal = 0;
%>

*/