package oscarblancarte.ipd.state.states;

import oscarblancarte.ipd.state.Server;

public class StopServerState extends AbstractServerState {
    private Thread stop;
    public StopServerState(Server server){
//        server.getMessageProcess().stop();

  		stop= new Thread(new Runnable() {
				
				@Override
				public void run() {
				try {
					while(true) {
						if (server.getMessageProcess()
                                .countMessage() >0) {
                            server.setState(
                                    new SegureStopState(server));
                            break;
                        }
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				}
			});
  		stop.start();
  	
  		
    }
//    

    @Override
    public void handleMessage(Server server, String message) {
    	
    	System.out.println("Server stoped");
        
    }
}
