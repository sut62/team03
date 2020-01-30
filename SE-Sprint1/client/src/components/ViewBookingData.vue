<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Booking Information</h1>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-col cols="6">
        <v-text-field label="ชื่อชมรม" v-model="clubname" outlined></v-text-field>
      </v-col>
      <v-col cols="1">
        <v-btn style="float: right" x-large color="primary" @click="search">Search</v-btn>
      </v-col>
      <v-col cols="1">
        <v-btn style="float: right" x-large color="primary" @click="reset">Reset</v-btn>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="tempItem" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>
    
   <v-row justify="center">
            <v-col cols="1">
                <v-bottom-navigation :value="activeBtn" color="deep-purple accent-4">

            <v-btn >

              <span><router-link to="/Booking"> ย้อนกลับ </router-link></span>

            </v-btn>

        

      </v-bottom-navigation>
            </v-col>
          </v-row>

      
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "BookingData",
  data() {
    return {
      headers: [
        
       
        { text: "Sitestaff", value: "sitestaff.name" },
        { text: "Clubname", value: "bookingClub.clubName" },
        { text: "Clubmember", value: "clubmember.name" },
        { text: "Room", value: "type.name" },
        { text: "Date", value: "bookingDate" }
      ],
      items: [],
      clubname: "",
      tempItem: [],
    }
  },
  methods: {
    /* eslint-disable no-console */
    reset() {
      this.tempItem = this.items
    },
    search() {
      if(this.clubname === "") {
        alert("กรุณาใส่ชื่อชมรมเพื่อค้นหา")
      } else {
        this.tempItem = []
        this.items.forEach(club => {
          if(club.bookingClub.clubName.includes(this.clubname)) {
            this.tempItem.push(club)
          }
        });
      }
    },
    getBooking() {
      http
        .get("/booking")
        .then(response => {
          this.items = response.data
          this.tempItem = this.items
          console.log(JSON.stringify(this.items))
        })
        .catch(e => {
          console.log(e)
        })
    },
    refreshList() {
      this.getBooking()
    }
    
  },
  mounted() {
    this.getBooking()
  }
}
</script>
