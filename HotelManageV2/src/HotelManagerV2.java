import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HotelManagerV2 {
	public static final int ROOMS=10; //number of rooms
	public static Room[] rooms = new Room[ROOMS]; //creating an array of room objects
	
	

	
	public static void main(String[] args) {
		
		//creating new objects in the object array
		for(int i=0;i<ROOMS;i++){
			rooms[i]=new Room();
		}
		
		 Update();//loading previous data
		 menu();//showing menu
		
	

	}
	
	
	
	
	
	
	
	public static void menu(){
		System.out.println();
		System.out.println("            Main Menu");
		System.out.println("    --------------------------");
		System.out.println("A: Add a customer to a room");
		System.out.println("V: View all rooms");
		System.out.println("E: Display Empty rooms");
		System.out.println("D: Delete customer from room");
		System.out.println("F: Find room from customer name");
		System.out.println("S: Store program data in to file");
		System.out.println("L: Load program data from file");
		System.out.println("O: View rooms Ordered alphabetically by name");
		System.out.println("or Press any other to exit");
		
		Scanner scn = new Scanner(System.in);
		String in = scn.nextLine().toUpperCase();
		run(in);
		
	}
	
	
	//directing user to asked function by the corresponding letter
	
	//do while loops have used in order to validate inputs
	public static void run(String choice){
		Scanner input = new Scanner(System.in);
		String in;
		
		switch(choice){
		
			case "A": String rNum,cName,number;
					
					  do{
						  System.out.println("Enter Room Number:");
						  rNum=input.nextLine();
					  }while( !( isValid(0,rNum) && isValid(3,rNum) ) );
					  
					  do{	
						  System.out.println("Enter Customer Name:");
						  cName=input.nextLine();
					  }while(!isValid(1,cName));
					  
					  do{	
						  System.out.println("Enter number of people in the room:");
						  number=input.nextLine();
					  }while(!isValid(2,number));
				  
					  add(rNum,cName,number);
				  
					  System.out.println("Your record was successfully recorded!");
					  confirm();
				  
				  
			break;
		case "V": for(int i=0;i<ROOMS;i++){
						if(!(rooms[i].getRoomNo()==null || rooms[i].getRoomNo().equals("null"))){
							System.out.println("--------------------");
							System.out.println("Room Number:-");
				 			System.out.println(rooms[i].getRoomNo());
				 			System.out.println("Customer Name:-");
				 			System.out.println(rooms[i].getcName());
				 			System.out.println("People in the room:-");
				 			System.out.println(rooms[i].getcCount());
						}else{
				 			System.out.println("--------------------");
							System.out.println("Room No."+(i+1)+" is empty");
						}
			 			
		 		   }
				   confirm();
		 case "E": System.out.println("Empty Rooms");
			 	   System.out.println("--------------------");
			 	   for(int i=0;i<ROOMS;i++){
						if(rooms[i].getRoomNo()==null|| rooms[i].getRoomNo().equals("null")){
							System.out.println("Room No:- "+(i+1));
						}
	 			
 		   		   }
			 	   confirm();
		 	break;
		 	
		  case "D": String user;
		  			do{	
		  				System.out.println("Enter Room Number you want to delete:");
		  				user=input.nextLine();
		  			}while(!isValid(0,user));
		  			int userN=Integer.parseInt(user);
		  			del(userN);
					confirm();
			 break;
		  case "F": 
		  			search();
					confirm();
		  	  break;
		  	  
		    case "S" : Save();
		    		   confirm();
		      break;
		  	  
		    case "L" : Update();
 		               confirm();
 		    break;  
 		    
		    case "O" : for(int i=0;i<ROOMS;i++){
							if(!(sortedRoom(rooms)[i].getRoomNo()==null || sortedRoom(rooms)[i].getRoomNo().equals("null"))){
								System.out.println("--------------------");
								System.out.println("Customer Name:-");
								System.out.println(sortedRoom(rooms)[i].getcName());
								System.out.println("Room Number:-");
								System.out.println(sortedRoom(rooms)[i].getRoomNo());
								System.out.println("People in the room:-");
								System.out.println(sortedRoom(rooms)[i].getcCount());
							}
	 			
		    		   }
		    		  confirm();

		    break;
		  	 
		  
		
		    default:System.out.println("Exiting...");
		    			System.exit(0);
		}
		
		
	}
	
	//deleting rooms
	public static void del(int num){
		rooms[num-1].setRoomNo(null);
		rooms[num-1].setcName(null);
		rooms[num-1].setcCount(null);

	}
	
	
	//adding room data to the room object array
	public static void add(String rNum,String cName,String number){
		int roomNo=Integer.parseInt(rNum)-1;
		rooms[roomNo].setRoomNo(rNum);
		rooms[roomNo].setcName(cName);
		rooms[roomNo].setcCount(number);
	}
	
	
	//All the validations to be done by the below method
	public static boolean isValid(int x,String in){
		boolean validity = true;
		switch(x){
		
		case 0:if(!in.matches("[0-9]+")){
				   System.err.println("Enter a number ");
				   validity=false;
			   }else if(!in.matches("[1-9]")){
				   System.err.println("Enter a number from 1 to 10 ");
				   validity=false;
			   }
			
			break;
		
		case 1:if(in.matches("[0-9]+")){
			   	   System.err.println("Enter a valid name ");
			       validity=false;
		       }
		
		    break;
		
		case 2:if(!in.matches("[0-9]+")){
		   	       System.err.println("Enter a valid number ");
		           validity=false;
	           }
	
			break;
			
		case 3:if(!(rooms[Integer.parseInt(in)-1].getRoomNo()==null||rooms[Integer.parseInt(in)-1].getRoomNo().equals("null"))){
	   	       	   System.err.println("Entered Room is already Booked ");
	   	       	   validity=false;
        	   }

		break;
			
		default:	
		    
			
		
		
		}
		return validity;
		
	}
	
	
	//searching
	public static void search(){
		Scanner scn = new Scanner(System.in);
		String inSearch;




		String re="";
		do{
			int x=0;
			re="";

			do{	
				System.out.println("Enter Customer Name to Search:");
				inSearch=scn.nextLine();
			}while(!isValid(1,inSearch));

			for(int i=0;i<ROOMS;i++){
				//converting to uppercase and then matches

				if( rooms[i].getcName()!=null && rooms[i].getcName().toUpperCase().equals(inSearch.toUpperCase()) ){
					System.out.println("--------------------");
					System.out.println("Room Number:-");
					System.out.println(rooms[i].getRoomNo());
					System.out.println("Customer Name:-");
					System.out.println(rooms[i].getcName());
					System.out.println("People in the room:-");
					System.out.println(rooms[i].getcCount());
					System.out.println("--------------------");
					x++;
				}
			}
			if(x==0){
				System.out.println("No record found in that name!");
				System.out.println("Press R to retry another search or any to go to Menu");
				re=scn.nextLine();

			}
		}while(re.toUpperCase().equals("R"));
		confirm();


	}

	//saving program data to a file
	public static void Save(){

		 try {
			 
		    PrintWriter writer = new PrintWriter(new File("programData.txt"));

		    for(int i=0; i<ROOMS; i++){

		    	writer.write(rooms[i].getRoomNo()+"-"+rooms[i].getcName()+"-"+rooms[i].getcCount());
		    	writer.println(); //leave one line 
		    }

		    writer.flush();  
		    writer.close();        



		   } catch (FileNotFoundException e) {      
		     e.printStackTrace();
		   }
	}
	
	
	//retrieving saved data
	public static void Update(){

		 try {

			 Scanner scan = new Scanner(new File("programData.txt"));

				//Retrieve
				        for(int i=0; i<ROOMS;  i++){
				        	String line = new String(scan.nextLine());
				        	if(rooms[i].getRoomNo()!=null){
				        		continue;
				        	}
				        	String[] data = line.split("-");
				            rooms[i].setRoomNo(data[0]);
				            rooms[i].setcName(data[1]);
				            rooms[i].setcCount(data[2]);//accending order

				        }

				     scan.close(); 

			 
			 
			 
				   //if not found a file the program will create a new file
				   //this happens only at the first execution of the program
		  } catch (FileNotFoundException e){
			  try{
				    PrintWriter writer = new PrintWriter("programData.txt");
				    for(int i=0;i<ROOMS;i++){
				    	writer.println("null-null-null");
				    	
				    }
				    
				    writer.close();
				} catch (IOException e1) {
				   
				}
			  
			  }    


		}
	
	
	
	//confirmation
	public static void confirm(){
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter Y to go to menu or any to exit");
		String in=scn.nextLine();
		if(in.toUpperCase().equals("Y")){
			menu();
		}else{
			System.out.println("Exiting....");
			System.exit(0);
		}
		
		  
	}
	
	//sortin the room object array
	public static Room[] sortedRoom(Room[] arrRm) {
		//new sorted array
		Room[] sortedRm =arrRm.clone();
		
		//comparator for sorting
		Comparator<Room> ComparatorCName = new Comparator<Room>() {
			//first name sorting
			public int compare(Room r1, Room r2) {
			   String r1cName = r1.getcName().toUpperCase();
			   String r2cName = r2.getcName().toUpperCase();

			   //ascending order
			   return r1cName.compareTo(r2cName);

			  
		    }
		};
		
		Arrays.sort(sortedRm,ComparatorCName);
		return sortedRm;
        
       

    }


	
}





