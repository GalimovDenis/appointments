package com.appointments.net.dto;

public enum RequestType {
	CREATE{ 
		public String getURN(){
			return "create";}
		},
	READ{ 
		public String getURN(){
			return "read";}
		},
	UPDATE{ 
		public String getURN(){
			return "update";}
		},
	DELETE{ 
		public String getURN(){
			return "delete";}
		};
	
	public abstract String getURN();
}
