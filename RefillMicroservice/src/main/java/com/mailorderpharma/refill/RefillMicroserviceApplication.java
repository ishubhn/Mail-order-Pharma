package com.mailorderpharma.refill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com")
public class RefillMicroserviceApplication {

//	@Autowired
//	Controller ser;

	public static void main(String[] args) {
		SpringApplication.run(RefillMicroserviceApplication.class, args);
//		RefillMicroserviceApplication refill = new RefillMicroserviceApplication();
//		refill.timerStart();
	}

//	public void timerStart()
//	{
//		try
//		{
//			ser.startTimer();
//		}
//		catch(Exception e)
//		{
//			System.out.println("exaception printed: "+e);
//		}
//		Timer timer = new Timer();
//		TimerTask tt = new TimerTask(){
//			public void run(){
//				try
//				{
//				System.out.println("inside ....");
//				System.out.println(ser.UpdateRefill());
//				}
//				catch(Exception e)
//				{
//					System.out.println("exaception printed: "+e);
//				}
//			}
//		};
//		timer.schedule(tt, 1000, 1000*60);  //delay the task 1 second, and then run task every five seconds
//	
//	}

}
