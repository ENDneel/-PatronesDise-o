package oscarblancarte.ipd.adapter.creditapi.Zbank;


public class ZBankCreditAPI {

	    
	    public ZBankCreditResponse sendCreditRequest(ZBankCreditRequest request){
	        ZBankCreditResponse response =new ZBankCreditResponse();
	        if(request.getRequestAmount()<= 7000){
	            response.setAproval(true);
	        }else{
	            response.setAproval(false);
	        }
	        return response;
	    }
	
}
