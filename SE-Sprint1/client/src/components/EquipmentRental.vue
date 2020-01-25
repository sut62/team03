<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to Equipment Rental</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row>
            <v-col cols="12">
              <v-select
                label="Clubs"
                outlined
                v-model="equipmentRental.club_id"
                :items="Clubs"
                  item-text="clubName"
                  item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Renter name"
                v-model="equipmentRental.renter_name"
                outlined
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-select
                label="Equipment"
                outlined
                v-model="equipmentRental.equipment_id"
                :items="equipments"
                item-text="e_name"
                item-value="e_id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-select
                label="Officer name"
                outlined
                v-model="equipmentRental.officer_id"
               :items="Officer" item-text="name" item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="12">
              <v-textarea v-model="equipmentRental.note" auto-grow outlined label="Note" rows="3"></v-textarea>
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-col cols="12">
              <v-btn @click="saveEquipmentRental" :class="{ red: !valid, green: valid }">Save</v-btn>
              <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              <v-btn style="float: right" color="primary" @click="viewData">ดูข้อมูล</v-btn>
            </v-col>
          </v-row>
          <br />
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common"

export default {
  name: "EquipmentRental",
  data() {
    return {
      equipmentRental: {
        club_id: "",
        equipment_id: "",
        officer_id: "",
        note: "",
        renter_name: ""
      },
      Clubs: [],
      Officer: [],
      equipments: [],
      valid: false,
      foo: 0
    }
  },
  methods: {
    /* eslint-disable no-console */
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
    getEquipments() {
      http
        .get("/equipments")
        .then(response => {
          this.equipments = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    // @PostMapping("/equipmentrental/{club_id}/{officer_id}/{equipment_id}/{note}/{renter_name}")

    saveEquipmentRental() {
      if (this.equipmentRental.note === "") {
        this.equipmentRental.note = "null"
      }
      http
        .post(
          "/equipmentrental/" +
            this.equipmentRental.club_id +
            "/" +
            this.equipmentRental.officer_id +
            "/" +
            this.equipmentRental.equipment_id +
            "/" +
            this.equipmentRental.note +
            "/" +
            this.equipmentRental.renter_name,
          this.equipmentRental
        )
        .then(() => {
          alert("บันทึกข้อมูลสำเร็จ")
          this.equipmentRental.club_id = ""
          this.equipmentRental.officer_id = ""
          this.equipmentRental.equipment_id = ""
          this.equipmentRental.note = ""
          this.equipmentRental.renter_name = ""
        })
        .catch(e => {
          console.log(e)
        })
      this.submitted = true
    },
    clear() {
      this.$refs.form.reset()
    },
    refreshList() {
      this.getClubs()
      this.getOfficer()
      this.getEquipments()
    },
    viewData() {
      this.$router.push("/viewequipmentrental")
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getClubs()
      this.getOfficer()
      this.getEquipments()
  }
}
</script>
