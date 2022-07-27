package oscarblancarte.ipd.state.states;

import oscarblancarte.ipd.state.Server;

public class SegureStopState extends AbstractServerState{
	
	public SegureStopState(Server server) {
		server.getMessageProcess().stopsecure();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(Server server, String message) {
		if(!server.getMessageProcess().endMessage()) {
			System.out.println("The system cannot send any more messages");
		}else {
			System.out.println("Server Stoped");
		}
		
		
		
		
	}
}
