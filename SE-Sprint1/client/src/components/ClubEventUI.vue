<template>
  <v-container>

    <v-card-title class="text-center justify-center py-6">
      <h3 class="font-weight-bold  basil--text">ระบบการจัดกิจกรรมชมรม</h3>
    </v-card-title>

    <v-row justify="center">
      <v-col cols="4">

        <v-form v-model="valid" ref="form">


    <!-- เลือกชื่อชมรม -->
          <v-row>
              <v-col cols="10">
                <v-select
                  label="ชมรม"
                  outlined
                  v-model="ClubEvent.ClubsID"
                  :items="Clubs"
                  item-text="clubName"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือก ชมรม']"
                  required
                ></v-select>
                </v-col>
          </v-row>

      <!-- ชื่อกิจกรรม -->
        <v-row>
        <v-text-field
          v-model="ClubEvent.ClubEventName"
          label="ชื่อกิจกรรม"
          style="width: 125px"
          type="String"
          @keydown="false"
          :rules="[(v) => !!v || 'กรุณากรอก ชื่อกิจกรรม']"
          required
        ></v-text-field>
        </v-row>

      <!-- สถานที่จัดกิจกรรม -->
          <v-row>
              <v-col cols="10">
                <v-select
                  label="สถานที่จัดกิจกรรม"
                  outlined
                  v-model="ClubEvent.LocationID"
                  :items="Locations"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือก สถานที่จัดกิจกรรม']"
                  required
                ></v-select>
                </v-col>
          </v-row>

        <!-- เลือกผู้ขอจัดกิจกรรม -->
          <v-row>
              <v-col cols="10">
                <v-select
                  label="ผู้ขอจัดกิจกรรม"
                  outlined
                  v-model="ClubEvent.ClubsHost"
                  :items="Clubs" item-text="advisors" item-value="advisors"
                  :rules="[(v) => !!v || 'กรุณาเลือก ผู้ขอจัดกิจกรรม']"
                  required
                ></v-select>
                </v-col>       
          </v-row>


        <!-- ชื่อกิจกรรม -->
        <v-row>
          <!-- <md-field>
        <label for="snackbar-duration">Miliseconds</label>
        <md-input type="number" id="snackbar-duration" v-model.number="duration" :disabled="isInfinity"></md-input>
        </md-field> -->

        <v-text-field
          v-model="ClubEvent.ClubEventPepleAmount"
          label="จำนวนผู้เข้าร่วม"
          style="width: 125px"
          type="Number"
          min = 0
          @keydown="false"
          :rules="[(v) => !!v || 'กรุณากรอก จำนวนผู้เข้าร่วม']"
          required
        ></v-text-field>

        </v-row>

        <!-- เลือก office -->
          <v-row>
              <v-col cols="10">
                <v-select
                  label="ผู้รับผิดชอบ"
                  outlined
                  v-model="ClubEvent.OfficerID"
                  :items="Officer" item-text="name" item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือก ผู้รับผิดชอบ']"
                  required
                ></v-select>
                </v-col>       
          </v-row>

    <!-- วัน เดือน ปี ที่นัด -->

    <v-row>
        <v-col cols="10">
        <v-menu
          ref="startMenu"
          v-model="ClubEvent.startMenu"
          :close-on-content-click="false"
          :nudge-right="40"
          :return-value.sync="ClubEventDate"
          transition="scale-transition"
          min-width="290px"
          offset-y
          full-width  
          >
          
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="ClubEventDate"
              label="วัน เดือน ปี"
              readonly
              v-on="on"
              :rules="[(v) => !!v || 'กรุณาเลือก วันที่จัดกิจกรรม']"
             required
            ></v-text-field>

          </template>
          <v-date-picker v-model="ClubEvent.ClubEventDate" no-title scrollable 
                :items="ClubEventDate"
                 >
            <div class="flex-grow-1"></div>
            <v-btn text color="primary" @click="startMenu = false" >Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.startMenu.save(ClubEvent.ClubEventDate)">OK</v-btn>
          </v-date-picker>
        </v-menu>
        </v-col>
    </v-row>
          

        <!--ปุ่มกด--> 
            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="save" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                 <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>

            <v-bottom-navigation :value="activeBtn" color="deep-purple accent-4">
            <v-btn >
              <span><router-link to="/Login"> logOut </router-link></span>
            </v-btn>
        
      </v-bottom-navigation>
            
            <br />
         
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "ClubEvent",
  data() {
    return {
      ClubEvent: {
    
        ClubsID:"",
        ClubEventName:"",
        LocationID: "",
        ClubsHost:"",
        ClubEventPepleAmount:"",
        OfficerID: "",
        ClubEventDate:""
        
      },
      Clubs: [],
      Locations: [],
      Officer: [],
      Advisor:[], 
      valid: false  
    };
  },
  
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Clubs ใส่ combobox
    getClubs() {
      http
        .get("/Clubs")
        .then(response => {
          this.Clubs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล Locations ใส่ combobox
    getLocations() {
      http
        .get("/Locations")
        .then(response => {
          this.Locations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getOfficer() {
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
  
    
    //เมื่อกดปุ่ม บันทึก
    save() {
      http
        .post(
          "/ClubEvent/" +
            this.ClubEvent.ClubsID +
            "/" +
            this.ClubEvent.ClubEventName +
            "/" +
            this.ClubEvent.LocationID +
            "/"+
            this.ClubEvent.ClubEventDate +
            "/"+
            this.ClubEvent.ClubEventPepleAmount +
            "/"+
            this.ClubEvent.ClubsHost +
            "/"+
            this.ClubEvent.OfficerID,
        this.ClubEvent
        )
        .then(response => {
          console.log(response.data);
          this.ClubEvent.ClubsID = "";
          this.ClubEvent.ClubEventName = "";
          this.ClubEvent.LocationID = "";
          this.ClubEvent.ClubEventDate = "";
          this.ClubEvent.ClubEventPepleAmount = "";
          this.ClubEvent.ClubsHost = "";
          this.ClubEvent.OfficerID = "";
          if (response) {
            alert("นัดหมายสำเร็จ");
          }
          // this.$router.push("/view");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    //เมื่อกดปุ่ม clear
    clear() {
      this.$refs.form.reset();
      
    },

    refreshList() {
      this.getClubs();
      this.getLocations();
      this.getOfficer();
     
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getClubs();
    this.getLocations();
    this.getOfficer();
    
  }
};
</script>
