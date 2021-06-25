package by.fxg.metro2041.common.storage;

import java.io.Serializable;

public class ServerKit implements Serializable {
	public String name;
	public Long timeToUse;
	
	public ServerKit(String a, Long b) {
		this.name = a;
		this.timeToUse = b;
	}
}
