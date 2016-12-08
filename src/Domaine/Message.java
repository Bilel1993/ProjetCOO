package Domaine;

public class Message {
	int idMessage;
	int idPersonne;
	String messageRecu;
	
	public Message(int idMessage, int idPersonne, String messageRecu) {
		this.idMessage = idMessage;
		this.idPersonne = idPersonne;
		this.messageRecu = messageRecu;
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

	public String getMessageRecu() {
		return messageRecu;
	}

	public void setMessageRecu(String messageRecu) {
		this.messageRecu = messageRecu;
	}

	
	
}
