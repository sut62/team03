<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">EquipmentRental Information</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "EquipmentRentalData",
  data() {
    return {
      headers: [
        { text: "Club room", value: "club.c_name" },
        { text: "Equipment", value: "equipment.e_name" },
        { text: "Officer", value: "officerEquipment.o_name" },
        { text: "Date", value: "equipmentRental_date" },
        { text: "Renter name", value: "renter_name" },
        { text: "Note", value: "note" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getEquipmentRental() {
      http
        .get("/equipmentrentals")
        .then(response => {
          this.items = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getEquipmentRental();
    }
  },
  mounted() {
    this.getEquipmentRental();
  }
};
</script>
