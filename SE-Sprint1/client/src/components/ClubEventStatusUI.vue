<template>
  <v-container>

    <v-card-title class="text-center justify-center py-6">
      <h3 class="font-weight-bold  basil--text">ระบบอนุมัติการจัดกิจกรรมชมรม</h3>
    </v-card-title>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">

            <!-- เลือกชมรมที่ต้องการอนุมัติ -->
      <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกกิจกรรม"
                  outlined
                  v-model="ClubEvents"
                  :items="ClubEvent"
                  item-text="clubEventName"
                  item-value="id"     
                  :rules="[(v) => !!v || 'กรุณาเลือก กิจกรรม']"
                  required
                >
                <!-- อยากเเสดงClubEventName เวลาเลือก-->
                </v-select>
                </v-col>
          </v-row> 

          <v-row>
                <v-text-field
                    v-model="ClubEvents"
                    :items="ClubEvent"
                    item-text="clubEventID" 
                    item-value="id"
                    style="width: 125px"
                    required
                ></v-text-field>
            </v-row>
                        
<v-card class="mx-auto" max-width="500">
    <v-list>
      <v-list-item-group v-model="ClubEvents">
        <v-list-item v-for="(item, i) in items" :key="i">
          <v-list-item-content>
            <v-list-item-title v-text="item.text"></v-list-item-title>
          </v-list-item-content>

        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>

        <!--ปุ่มกด--> 
        <div>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="save" :class="{ red: !valid, green: valid }">อนุมัติ</v-btn>
                 <v-btn style="margin-left: 15px;" @click="clear">ยกเลิกกิจกรรม</v-btn>
              </v-col>
            </v-row>
        </div>
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
      ClubEvent: [],
      items: [
        {
          text: 'clubEventName',
        },
        {
          text: 'clubs',
        },
        {
          text: 'location',
        },
        {
          text: 'officer',
        },
      ],
      valid: false  
    };
  },
// ดึงข้อมูลจาก ClubEvent ที่เลือกมาเเสดง
   
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล ClubEvent ใส่ combobox
    getClubEvent() {
      http
        .get("/ClubEvent")
        .then(response => {
          this.ClubEvent = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  
    
    // เมื่อกดปุ่ม อนุมัติ ให้ update กิจกรรมที่เลือก ตรง ClubEventStatus จาก รออนุมัติเป็นอนุมัติ
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
            this.$refs.form.reset();
            alert("อนุมัติสำเร็จ");
          }
          // this.$router.push("/view");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },

    // เมื่อกดปุ่ม ยกเลิกกิจกรรม ให้ ลบ กิจกรรม
    clear() {
      this.$refs.form.reset();
      
    },

    refreshList() {
      this.getClubEvent();

     
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getClubEvent(); 
  }
};
</script>
