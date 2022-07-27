package oscarblancarte.ipd.decorator.impl.decorators;

import oscarblancarte.ipd.decorator.impl.message.IMessage;

public class MessageBDecorator extends MessageDecorator{

	private String name;
	private String address;
	private String telephone;
	private String email;

	public MessageBDecorator(String name, String address, String telephone, String email,IMessage message) {
		super(message);
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public IMessage processMessage() {
		message.processMessage();
		message= footer();
		return message;
	}

	private IMessage footer() {
		try {
			String footer =message.getContent()
	                + "   <soapenv:Footer>\n"
	                + name + ", "+address+", "+telephone+", "+email
	                + "\n"
					+ "   </soapenv:Footer>\n"
	                + "</soapenv:Envelope>";
			message.setContent(footer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return message;
	}

}
