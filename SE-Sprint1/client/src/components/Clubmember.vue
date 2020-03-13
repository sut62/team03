<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to Clubmember Apply</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row>
            <v-col cols="10">
              <v-select
                label="ชื่อชมรม"
                outlined
                v-model="Clubmember.Club_id"
                :items="Clubs"
                item-text="clubName"
                item-value="id"
                :rules="[(v) => !!v || 'เลือกชื่อชมรม']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="10">
              <v-text-field
                outlined
                label="ชื่อ"
                v-model="Clubmember.name"
                :rules="[(v) => !!v || 'ใส่ชื่อ']"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="10">
              <v-select
                label="สาขาวิชา"
                outlined
                v-model="Clubmember.Branch_id"
                :items="Branch"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'เลือกสาขาวิชา']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="4">
              <v-select
                label="ชั้นปี"
                outlined
                v-model="Clubmember.Year_id"
                :items="Year"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'เลือกชั้นปี']"
                required
              ></v-select>
            </v-col>
          </v-row>

             <v-row justify="center">
        <v-col cols="7">
          <v-row justify="center">
            <v-col cols="6">
              <div
                v-if="saveStatus.isSuccess"
                style="border: 1px solid #79FFBA; border-radius: 5px; background-color: #607D8B; align-items: center"
              >
                <div style="padding: 15px; color: ">{{saveStatus.message}}</div>
              </div>
              <div
                v-if="saveStatus.isFail"
                style="border: 1px solid #FFA879; border-radius: 5px; background-color: #607D8B; align-items: center"
              >
                <div style="padding: 15px; color: #FF3D00">{{saveStatus.message}}</div>
              </div>
              
            </v-col>
          </v-row>
        </v-col>
      </v-row>

          <v-row justify="center">
            <v-col cols="12">
              <v-btn @click="saveClubmember" :class="{ red: !valid, green: valid }">ยืนยัน</v-btn>
              <v-btn style="margin-left: 15px;" @click="clear">ยกเลิก</v-btn>
            </v-col>
          </v-row>

          <br />

          <v-row justify="center">
            <v-btn style="margin-left: 15px;" color="#29B6F6" v-on:click="Clubmemberview">ดูข้อมูล</v-btn>
          </v-row>

          <br />
          <v-row justify="left">
            <v-btn style="margin-left: 15px;" color="#00838F" v-on:click="ClubHome">ฺBack</v-btn>
              <v-row justify="center">
            <v-btn style="margin-left: 15px;" color="yellow accent-4" v-on:click="Login">Logout</v-btn>
          </v-row>
          </v-row>

       
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common"

export default {
  name: "Clubmember",
  data() {
    return {
      Clubmember: {
        Branch_id: "",
        Year_id: "",
        Club_id: "",
        name: ""
      },
      Branch: [],
      Clubs: [],
      Year: [],
      valid: false,
       saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Branch ใส่ combobox
    getBranchs() {
      http
        .get("/Branch")
        .then(response => {
          this.Branch = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    // ดึงข้อมูล Club ใส่ combobox
    getClubs() {
      http
        .get("/Clubs")
        .then(response => {
          this.Clubs = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    // ดึงข้อมูล Year ใส่ combobox
    getYears() {
      http
        .get("/Year")
        .then(response => {
          this.Year = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    // function เมื่อกดปุ่ม ยืนยัน
    saveClubmember() {
        http
          .post(
            "/Clubmember/" +
              this.Clubmember.Branch_id +
              "/" +
              this.Clubmember.Year_id +
              "/" +
              this.Clubmember.Club_id +
              "/" +
              this.Clubmember.name,
            this.Clubmember
          )
          .then(response => {
             if (response) {
            this.saveStatus.message = "บันทึกข้อมูลสำเร็จ"
            this.saveStatus.isSuccess = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
            }, 5000)
          } else {
            this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
            this.saveStatus.isFail = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 5000)
          }
        })
        .catch(() => {
          this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
          this.saveStatus.isFail = true
           setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 5000)
        })
    },
    clear() {
      this.$refs.form.reset()
    },

    Clubmemberview() {
      this.$router.push("/Clubmemberview")
    },
    Login() {
      this.$router.push("/Login")
    },

       ClubHome() {
      this.$router.push("/ClubHome")
    },

    refreshList() {
      this.getBranchs()
      this.getClubs()
      this.getYears()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getBranchs()
    this.getClubs()
    this.getYears()
  }
}
</script>
