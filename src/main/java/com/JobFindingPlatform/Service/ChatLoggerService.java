package com.JobFindingPlatform.Service;

import org.springframework.stereotype.Service;

@Service
public class ChatLoggerService {
	
	public void logConnection(String message) {
		System.out.println("WebSockect Log:"+message);
	}

}


