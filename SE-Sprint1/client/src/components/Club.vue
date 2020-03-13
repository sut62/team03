<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to Club apply</h1>
      </v-flex>
    </v-layout>
   
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row>
            <v-col cols="10">
              <v-text-field
                label="ชื่อชมรม"
                outlined
                v-model="Club.ClubName"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
          </v-row>

             <v-row>
              <v-col cols="10">
                <v-text-field
                      outlined
                      label="ชื่อประธานชมรม"
                      v-model="Club.ClubPresident"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
              </v-col>
              </v-row>

                     <v-row>
            <v-col cols="10">
              <v-select
                label="สาขาวิชา"
                outlined
                v-model="Club.Branch_id"
                :items="Branch"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

               <v-row>
            <v-col cols="4">
              <v-select
                label="ชั้นปี"
                outlined
                v-model="Club.Year_id"
                :items="Year"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>
           
              <v-row>
            <v-col cols="10">
              <v-select
                label="ประเภทชมรม"
                outlined
                v-model="Club.ClubType_id"
                :items="ClubType"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

                  <v-row>
              <v-col cols="10">
                <v-text-field
                      outlined
                      label="วัตถุประสงค์ในการตั้งชมรม"
                       v-model="Club.Objective"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
              </v-col>
              </v-row>
              
                  <v-row>
              <v-col cols="10">
                <v-text-field
                      outlined
                      label="อาจารย์ที่ปรึกษาในชมรม"
                        v-model="Club.advisors"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
              </v-col>
              </v-row>
          
          <v-row>
            <v-col cols="10">
              <v-select
                label="เจ้าหน้าที่"
                outlined
                v-model="Club.Officer_id"
                :items="Officer"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>
          
            <v-row justify="center">
        <v-col cols="7">
          <v-row justify="center">
            <v-col cols="6">
              <div
                v-if="saveStatus.isSuccess"
                style="border: 1px solid #79FFBA; border-radius: 5px; background-color: #607D8B; align-items: center">
                <div style="padding: 15px; color: ">{{saveStatus.message}}</div>
              </div>
              <div
                v-if="saveStatus.isFail"
                style="border: 1px solid #FFA879; border-radius: 5px; background-color: #607D8B; align-items: center">
                <div style="padding: 15px; color: #FF3D00">{{saveStatus.message}}</div>
              </div>
              
            </v-col>
          </v-row>
        </v-col>
      </v-row>
         

          <v-row justify="center">
            <v-col cols="12">
              <v-btn @click="saveClub" :class="{ red: !valid, green: valid }">ยืนยัน</v-btn>
              <v-btn style="margin-left: 15px;" @click="clear">ยกเลิก</v-btn>
              </v-col>
          </v-row>
          <br />

         <v-row justify="left">
            <v-btn style="margin-left: 15px;" color="#00838F" v-on:click="ClubHome">ฺBack</v-btn>
              <v-row justify="center">
            <v-btn style="margin-left: 15px;" color="yellow accent-4" v-on:click="Login">Logout</v-btn>
          </v-row>
          </v-row>

          
        </v-form>
      </v-col>
     </v-row>
          
        
        

 
   
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "Club",
  data() {
    return {
      Club: {
        Branch_id: "",
        Year_id: "",
        Officer_id: "",
        ClubType_id: "",
        ClubName: "",
        ClubPresident: "",
        Objective: "",
        advisors: ""
        
      },
      Branchs: [],
      Yesrs: [],
      Officers: [],
      ClubTypes: [],
      valid: false,
       saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
      
    };
  },
  methods: {
    /* eslint-disable no-console */
  
    // ดึงข้อมูล Branch ใส่ combobox
    getBranchs() {
      http
        .get("/Branch")
        .then(response => {
          this.Branch = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล Officer ใส่ combobox
    getOfficers() {
      http
        .get("/Officer")
        .then(response => {
          this.Officer = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล Year ใส่ combobox
    getYears() {
      http
        .get("/Year")
        .then(response => {
          this.Year = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล ClubType ใส่ combobox
       getClubTypes() {
      http
        .get("/ClubType")
        .then(response => {
          this.ClubType = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม ยืนยัน
    saveClub() {
      http
        .post(
          "/Clubs/" +
            this.Club.Branch_id +
            "/" +
            this.Club.Year_id +
            "/" +
            this.Club.Officer_id +
            "/" +
            this.Club.ClubType_id +
            "/" +
            this.Club.ClubName +
            "/" +
            this.Club.ClubPresident +
            "/" +
            this.Club.Objective +
            "/" +
            this.Club.advisors,
            this.Club
            
           )
              .then(response => {
          if (response) {
            this.saveStatus.message = "บันทึกข้อมูลสำเร็จ"
            this.saveStatus.isSuccess = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
            }, 5000)
          } else {
            this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
            this.saveStatus.isFail = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 5000)
          }
        })
        .catch(() => {
          this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
          this.saveStatus.isFail = true
           setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 5000)
        })
    },
    clear() {
      this.$refs.form.reset()
    },

     Login() {
      this.$router.push("/Login")
    },

     ClubHome() {
      this.$router.push("/ClubHome")
    },

    refreshList() {
      this.getBranchs();
      this.getOfficers();
      this.getYears();
      this.getClubTypes();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getBranchs();
    this.getOfficers();
    this.getYears();
    this.getClubTypes();
  }
};
</script>
