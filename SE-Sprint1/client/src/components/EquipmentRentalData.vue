<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">EquipmentRental Information</h1>
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
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "EquipmentRentalData",
  data() {
    return {
      headers: [
        { text: "Equipment Rental ID", value: "equipmentRental_id" },
        { text: "ชื่อชมรม", value: "clubs.clubName" },
        { text: "อุบปกรณ์", value: "equipment.e_name" },
        { text: "เจ้าหน้าที่", value: "officer.name" },
        { text: "วันที่ยืมอุปกรณ์", value: "equipmentRental_date" },
        { text: "ผู้ยืมอุปกรณ์", value: "renter_name" },
        { text: "Note", value: "note" }
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
          if(club.clubs.clubName.includes(this.clubname)) {
            this.tempItem.push(club)
          }
        });
      }
    },
    getEquipmentRental() {
      http
        .get("/equipmentrentals")
        .then(response => {
          this.items = response.data
          this.tempItem = this.items
          console.log(this.items)
        })
        .catch(e => {
          console.log(e)
        })
    },
    refreshList() {
      this.getEquipmentRental()
    }
  },
  mounted() {
    this.getEquipmentRental()
  }
}
</script>
