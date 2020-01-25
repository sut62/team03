<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Budget Information</h1>
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
  name: "BudgetData",
  data() {
    return {
      headers: [
        { text: "Budget ID", value: "budgetId"},
        { text: "ชื่อชมรม", value: "clubs.clubName" },
        { text: "ประเภทชมรม", value: "clubType.clubTypeName" },
        { text: "จำนวน", value: "amount" },
        { text: "ชื่อผู้เบิก", value: "clubmember.name" }
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
    getBudget() {
      http
        .get("/budget")
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
      this.getBudget()
    }
  },
  mounted() {
    this.getBudget()
  }
}
</script>
