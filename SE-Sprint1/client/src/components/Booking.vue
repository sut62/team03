<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to Booking</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row>
            <v-col cols="10">
              <v-select
                label="Site Staff"
                outlined
                v-model="booking.sitestaff_id"
                :items="Sitestaff"
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
                label="Club"
                outlined
                v-model="booking.club_id"
                :items="Clubs"
                item-text="clubName"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="10">
              <v-select
                label="Club Member"
                outlined
                v-model="booking.clubmember_id"
                :items="Clubmember"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
                @input="selectClubmember"
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="10">
              <v-select
                label="Room"
                outlined
                v-model="booking.room_id"
                :items="Room"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="10">
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field outlined v-model="date" label="วันที่ทำรายการ" readonly v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <div class="flex-grow-1"></div>
                  <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-col cols="12">
              <v-btn @click="savebooking" :class="{ red: !valid, green: valid }">submit</v-btn>
              <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
            </v-col>
          </v-row>
          <br />
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "booking",
  data() {
    return {
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      booking: {
        price: "",
        sitestaff_id: "",
        club_id: "",
        clubmember_id: "",
        room_id: ""
      },
      
      Sitestaff: [],
      Clubmember: [],
      Clubs: [],
      Room: [],
      valid: false
    };
  },
  methods: {
    
    /* eslint-disable no-console */

    // ดึงข้อมูล Sitestaff ใส่ combobox
    getSitestaffs() {
      http
        .get("/sitestaff")
        .then(response => {
          this.Sitestaff = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Club ใส่ combobox
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
    // ดึงข้อมูล Clubmember ใส่ combobox
    getClubmember() {
      http
        .get("/clubmember")
        .then(response => {
          this.Clubmember = response.data;
          console.log("Clubmember" + response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล Room()  ใส่ combobox
    getRoom() {
      http
        .get("/room")
        .then(response => {
          this.Room = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม submit
    savebooking() {
      http
        .post(
          "/booking/" +
            this.booking.club_id +
            "/" +
            this.booking.sitestaff_id +
            "/" +
            this.booking.clubmember_id +
            "/" +
            this.booking.room_id +
            "/" +
            this.date,
          this.booking
        )
        .then(response => {
          alert('ทำรายการสำเร็จ');
          console.log(response);
          //this.$router.push("/view");
        })
        .catch(e => {
          alert('ไม่สามารถทำรายการได้');
          console.log(e);
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getSitestaffs();
      this.getClubmember();
      this.getClubs();
      this.getRoom();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getSitestaffs();
    this.getClubmember();
    this.getClubs();
    this.getRoom();
  }
};
</script>