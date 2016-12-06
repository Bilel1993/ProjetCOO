package Domaine;

public class Message {
	int idMessage;
	int idPersonne;
	String messageReçu;
	
	public Message(int idMessage, int idPersonne, String messageReçu) {
		super();
		this.idMessage = idMessage;
		this.idPersonne = idPersonne;
		this.messageReçu = messageReçu;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getMessageReçu() {
		return messageReçu;
	}

	public void setMessageReçu(String messageReçu) {
		this.messageReçu = messageReçu;
	}

	
	
}
