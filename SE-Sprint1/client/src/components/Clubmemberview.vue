<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 cyan darken-3 text-center"
      >Clubmember View</h2>
    </v-flex>
        <br />
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
      <v-col cols="10">
        <v-data-table :headers="headers" :items="tempItem" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn style="margin-left: 15px;" color="#00838F" dark v-on:click="Clubmember">Back</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "ClubmemberView",
  data() {
    return {
      headers: [
        { text: "Clubmember ID", value: "id" },
        { text: "ชื่อนักศึกษา", value: "clubmemberName" },
        { text: "ชื่อชมรม", value: "clubs.clubName" },
        { text: "สาขาวิชา", value: "branch.name" },
        { text: "ปีการศึกษา", value: "year.name" },
      

      ],
      items: [],
      clubname: "",
      tempItem: [],
    }
  },
  methods: {
    /* eslint-disable no-console */
    Clubmember() {
        this.$router.push("/Clubmember")
    },
     reset() {
      this.tempItem = this.items
    },
    search() {
      if(this.clubname === "") {
        alert("กรุณาใส่ชื่อชมรมเพื่อค้นหา")
      } else {
        this.tempItem = []
        this.items.forEach(club => {
          if(club.clubs) {
            if(club.clubs.clubName.includes(this.clubname)) {
              this.tempItem.push(club)
            }

          }
        });
      }
    },
getClubmember() {
      http
        .get("/Clubmember")
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
      this.getClubmember()
    }
  },
  mounted() {
    this.getClubmember()
  }
}
</script>
