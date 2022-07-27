package oscarblancarte.ipd.observer.impl.observers;

import java.text.NumberFormat;
import java.util.Currency;

import oscarblancarte.ipd.observer.impl.ConfigurationManager;
import oscarblancarte.ipd.observer.impl.IObserver;

public class MoneyConvertObserver implements IObserver{

	@Override
	public void notifyObserver(String command, Object source) {
		// TODO Auto-generated method stub
		
		if(command.equals("moneyConvert")){
            ConfigurationManager conf = (ConfigurationManager)source;
         
            
            System.out.println("Observer ==> MoneyConvertObserver.moneyConvertChange > " 
                    + NumberFormat.getCurrencyInstance(conf.getLocalConvert()).format(10000));
        }
		
	}

}
