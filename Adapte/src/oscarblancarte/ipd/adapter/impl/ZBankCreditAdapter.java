package oscarblancarte.ipd.adapter.impl;

import oscarblancarte.ipd.adapter.creditapi.Zbank.ZBankCreditAPI;
import oscarblancarte.ipd.adapter.creditapi.Zbank.ZBankCreditRequest;
import oscarblancarte.ipd.adapter.creditapi.Zbank.ZBankCreditResponse;

public class ZBankCreditAdapter implements IBankAdapter{

	@Override
	public BankCreditResponse sendCreditRequest(BankCreditRequest request) {
		
		ZBankCreditRequest zrequest= new ZBankCreditRequest();
		//envio de datos para realizar la peticion
		zrequest.setCustomerName(request.getCustomer());
		zrequest.setRequestAmount(request.getAmount());
		//elemento que va a hacer uso de la API
		ZBankCreditAPI api= new ZBankCreditAPI();
		//mediante el elemento se envia la petición del crédito
		ZBankCreditResponse zresponse= api.sendCreditRequest(zrequest);
		//luego se verifica si el crédito es aprobado o no
		BankCreditResponse response= new BankCreditResponse();
		response.setApproved(zresponse.IsAproval());
		
		return response;
	}

}
