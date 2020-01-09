<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Booking Information</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "BookingData",
  data() {
    return {
      headers: [
        
        { text: "Sitestaff", value: "sitestaff.name" },
        { text: "Club", value: "bookingClub.name" },
        { text: "Clubmember", value: "clubmember.name" },
        { text: "BookingType", value: "type.name" },
        
        { text: "Date", value: "bookingDate" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล queue ใส่ combobox
    getBooking() {
      http
        .get("/booking")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getBooking();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getBooking();
  }
};
</script>

