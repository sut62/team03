package SE62.team03;
import SE62.team03.Repository.*;
import SE62.team03.Entity.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;

@SpringBootApplication
public class Team03Application {

	public static void main( String[] args) {
		SpringApplication.run(Team03Application.class, args);
	}
	@Bean
    ApplicationRunner init(
	 ClubEventRepository ClubEventRepository,  
	 ClubsRepository ClubsRepository,
	 OfficerRepository OfficerRepository,
	 LocationRepository LocationRepository,
	 BranchRepository BranchRepository,
	 YearRepository YearRepository,
	 ClubTypeRepository ClubTypeRepository,
	 SitestaffRepository sitestaffRepository,
	 RoomRepository roomRepository,
	 ClubmemberRepository ClubmemberRepository,
	 EquipmentRentalRepository equipmentRentalRepository,
	 EquipmentRepository equipmentRepository) {
		return args -> {


// ================================= สร้าง Object Location ===============================================
				Stream.of("เรียนรวม1", "เรียนรวม2", "สุรพัท1", "สุรพัท2","ลานหมอลำ","สนาม1","สนาม2","สนาม3"
				).forEach(name -> {
				Location Location = new Location(); // สร้าง Object 
				Location.setName(name); // set ชื่อ (name) ให้ Object ชื่อ 
				LocationRepository.save(Location); // บันทึก Objcet ชื่อ 
				});
				
// ================================= สร้าง Object Officer ===============================================
				Officer Officer = new Officer(); // สร้าง Object 
				Officer.setName("เจ้าหน้าที่ฝ่ายบุคคล"); // set ชื่อ (name) ให้ Object ชื่อ 			
				Officer.setUsername("10001");
				Officer.setPassword("1234");
				Officer.setOfficerStatus((long)1);
				OfficerRepository.save(Officer); // บันทึก Objcet ชื่อ

				Officer Officer2 = new Officer(); // สร้าง Object 
				Officer2.setName("เจ้าหน้าที่การเงิน"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer2.setUsername("20001");
				Officer2.setPassword("1234");
				Officer2.setOfficerStatus((long)2);
				OfficerRepository.save(Officer2); // บันทึก Objcet ชื่อ 

				Officer Officer3 = new Officer(); // สร้าง Object 
				Officer3.setName("เจ้าหน้าที่จัดกิจกรรมชมรม"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer3.setUsername("30001");
				Officer3.setPassword("1234");
				Officer3.setOfficerStatus((long)3);
				OfficerRepository.save(Officer3); // บันทึก Objcet ชื่อ 

				Officer Officer6 = new Officer(); // สร้าง Object 
				Officer6.setName("เจ้าหน้าที่จัดกิจกรรมชมรม2"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer6.setUsername("30002");
				Officer6.setPassword("1234");
				Officer6.setOfficerStatus((long)3);
				OfficerRepository.save(Officer6); // บันทึก Objcet ชื่อ 

				Officer Officer7 = new Officer(); // สร้าง Object 
				Officer7.setName("เจ้าหน้าที่จัดกิจกรรมชมรม3"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer7.setUsername("30003");
				Officer7.setPassword("1234");
				Officer7.setOfficerStatus((long)3);
				OfficerRepository.save(Officer7); // บันทึก Objcet ชื่อ 

				Officer Officer4 = new Officer(); // สร้าง Object 
				Officer4.setName("เจ้าหน้าที่ฝ่ายเช่าอุปกรณ์"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer4.setUsername("40001");
				Officer4.setPassword("1234");
				Officer4.setOfficerStatus((long) 4); // ใช้เช็คตอน login
				OfficerRepository.save(Officer4); // บันทึก Objcet ชื่อ 

				Officer Officer5 = new Officer(); // สร้าง Object 
				Officer5.setName("เจ้าหน้าที่ฝ่ายสถานที่"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer5.setUsername("50001");
				Officer5.setPassword("1234");
				Officer5.setOfficerStatus((long)5);
				OfficerRepository.save(Officer5); // บันทึก Objcet ชื่อ Clubs


				
// ================================= สร้าง Object Club ===============================================				
				Clubs Clubs = new Clubs(); // สร้าง Object Clubs
				Clubs.setName("ชมรมคอมพิวเตอร์"); // set ชื่อ (name) ให้ Object ชื่อ Clubs			
				Clubs.setAdvisor("มะลิ"); // set ชื่อ (name) ให้ Object ชื่อ Clubs
				Clubs.setClubPresident("มะละ");
				Clubs.setObjective("55555555");
				ClubsRepository.save(Clubs); // บันทึก Objcet ชื่อ Clubs
// ================================= สร้าง Object Clubmember ===============================================				
               Clubmember Clubmember = new Clubmember(); // สร้าง Object Clubmember
			   Clubmember.setName("พระจันทร์"); // set ชื่อ (name) ให้ Object ชื่อ Clubmember
			   ClubmemberRepository.save(Clubmember);// บันทึก Objcet ชื่อ Clubmember
				
// ================================= สร้าง Object ของปอน ===============================================
				Stream.of("วิศวกรรมคอมพิวเตอร์", "วิศวกรรมเครื่องกล", "เทคโนโลยีการผลิคสัตว์", "เทคโนโลยีสารสนเทศ").forEach(name -> {
					Branch Branch = new Branch(); // สร้าง Object Branch
					Branch.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Branch
					BranchRepository.save(Branch); // บันทึก Objcet ชื่อ Branch
				});
				Stream.of("1", "2", "3", "4").forEach(name -> {
					Year Year = new Year(); // สร้าง Object Year
					Year.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Year
					YearRepository.save(Year); // บันทึก Objcet ชื่อ Year
				});
	
				Stream.of("ชมรมวิชาการ", "ชมรมกีฬา", "ชมรมจิตอาสา").forEach(name -> {
					ClubType ClubType = new ClubType(); // สร้าง Object ClubType
					ClubType.setName(name); // set ชื่อ (name) ให้ Object ชื่อ ClubType
					ClubTypeRepository.save(ClubType); // บันทึก Objcet ชื่อ ClubType
				});
			
// ================================= สร้าง Object ของโจ้ ===============================================
				Stream.of("Site Staff1", "Site Staff2", "Site Staff3", "Site Staff4").forEach(name -> {
					Sitestaff sitestaff = new Sitestaff(); // สร้าง Object 
					sitestaff.setName(name); // set ชื่อ (name) ให้ Object ชื่อ 
					sitestaffRepository.save(sitestaff); // บันทึก Objcet ชื่อ 
				});
	
				Stream.of("Room1", "Room2", "Room3").forEach(name -> {
					Room room = new Room(); // สร้าง Object 
					room.setName(name); // set ชื่อ (name) ให้ Object ชื่อ 
					roomRepository.save(room); // บันทึก Objcet ชื่อ 
				});
	
			
// ================================= สร้าง Object ของมิ้น===============================================
				Stream.of("ตู้เก็บของ", "เครื่องปริ้น", "เลื่อยไฟฟ้า").forEach(e_name -> {
					Equipment equipment = new Equipment();
					equipment.setE_name(e_name);
					equipmentRepository.save(equipment);
				});

			//LocationRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Clubs บน Terminal
		
            
           
        };
	} 

}
