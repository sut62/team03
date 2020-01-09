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
	 ClubmemberRepository clubmemberRepository,
	 EquipmentRentalRepository equipmentRentalRepository,
	 EquipmentRepository equipmentRepository) {
		return args -> {


// ================================= สร้าง Object Location ===============================================
			    Stream.of("ภายในบริเวณมหาวิทยาลัย", "ภายนอกมหาวิทยาลัย", "Barnaby", "Cosmo","อื่นๆ").forEach(name -> {
				Location Location = new Location(); // สร้าง Object 
				Location.setName(name); // set ชื่อ (name) ให้ Object ชื่อ 
				LocationRepository.save(Location); // บันทึก Objcet ชื่อ 
				});
				
// ================================= สร้าง Object Officer ===============================================
				Officer Officer = new Officer(); // สร้าง Object 
				Officer.setName("เจ้าหน้าที่ฝ่ายบุคคล"); // set ชื่อ (name) ให้ Object ชื่อ 			
				Officer.setUsername("M1");
				Officer.setPassword("1234");
				Officer.setOfficerStatus(1);
				OfficerRepository.save(Officer); // บันทึก Objcet ชื่อ

				Officer Officer2 = new Officer(); // สร้าง Object 
				Officer2.setName("B"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer2.setUsername("M2");
				Officer2.setPassword("1234");
				Officer2.setOfficerStatus(2);
				OfficerRepository.save(Officer2); // บันทึก Objcet ชื่อ 

				Officer Officer3 = new Officer(); // สร้าง Object 
				Officer3.setName("เจ้าหน้าที่จัดกิจกรรมชมรม"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer3.setUsername("M3");
				Officer3.setPassword("1234");
				Officer3.setOfficerStatus(3);
				OfficerRepository.save(Officer3); // บันทึก Objcet ชื่อ 

				Officer Officer4 = new Officer(); // สร้าง Object 
				Officer4.setName("เจ้าหน้าที่ฝ่ายเช่าอุปกรณ์"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer4.setUsername("M4");
				Officer4.setPassword("1234");
				Officer4.setOfficerStatus(4);//ใช้เช็คตอน login
				OfficerRepository.save(Officer4); // บันทึก Objcet ชื่อ 

				Officer Officer5 = new Officer(); // สร้าง Object 
				Officer5.setName("เจ้าหน้าที่ฝ่ายสถานที่"); // set ชื่อ (name) ให้ Object ชื่อ 				
				Officer5.setUsername("M5");
				Officer5.setPassword("1234");
				Officer5.setOfficerStatus(5);
				OfficerRepository.save(Officer5); // บันทึก Objcet ชื่อ Doctor
				
// ================================= สร้าง Object Club ===============================================				
				Clubs Clubs = new Clubs(); // สร้าง Object Doctor
				Clubs.setName("อาสา"); // set ชื่อ (name) ให้ Object ชื่อ Doctor				
				Clubs.setAdvisor("มะลิ"); // set ชื่อ (name) ให้ Object ชื่อ Doctor
				Clubs.setClubPresident("มะละ");
				Clubs.setObjective("55555555");
				ClubsRepository.save(Clubs); // บันทึก Objcet ชื่อ Doctor


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
	
				Stream.of("วิชาการ", "กีฬา", "จิตอาสา").forEach(name -> {
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
	
				Stream.of("Club Member1", "Club Member2", "Club Member3").forEach(title -> {
					Clubmember clubmember = new Clubmember(); // สร้าง Object 				
					clubmember.setName(title); // set ชื่อ (name) ให้ Object  				
					clubmemberRepository.save(clubmember); // บันทึก Objcet 
				});

// ================================= สร้าง Object ของมิ้น===============================================
				Stream.of("ตู้เก็บของ", "เครื่องปริ้น", "เลื่อยไฟฟ้า").forEach(e_name -> {
					Equipment equipment = new Equipment();
					equipment.setE_name(e_name);
					equipmentRepository.save(equipment);
				});

			//LocationRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity doctor บน Terminal
		
            
           
        };
	} 

}
