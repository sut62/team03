<template>
  <v-container>
    <v-card-title class="text-center justify-center py-6">
      <h3 class="font-weight-bold basil--text">ระบบอนุมัติการจัดกิจกรรมชมรม</h3>
    </v-card-title>

    <v-row justify="center">
      <v-col cols="6">
        <v-form v-model="valid" ref="form">

          <!-- popupStatus -->

          <div
                v-if="saveStatus.isSuccess"
                style="border: 1px solid #79FFBA; border-radius: 5px; background-color: #B2FFD7; align-items: center"
              >
                <div style="padding: 15px; color: #029E4E">{{saveStatus.message}}</div>
              </div>
              <div
                v-if="saveStatus.isFail"
                style="border: 1px solid #FFA879; border-radius: 5px; background-color: #FFD6B2; align-items: center"
              >
                <div style="padding: 15px; color: #733600">{{saveStatus.message}}</div>
              </div>
   <!-- popupStatus -->

          <v-row>
            <v-col cols="10">
              <v-select
                label="เลือกกิจกรรม"
                outlined
                v-model="ClubEventID"
                :items="ClubEvent"
                item-text="clubEventName"
                item-value="clubEventID"
                :rules="[(v) => !!v || 'กรุณาเลือก กิจกรรม']"
                required
                @change="selectClubEvent"
              ></v-select>
            </v-col>
          </v-row>

          <v-row v-if="isSelected">
            <v-col cols="10">
              <v-card class="mx-auto" max-width="400">
                <v-card-title>{{clubName}}</v-card-title>
                <v-card-text class="text--primary">
                  <div>ชื่อกิจกรรม : {{clubEventName}}</div>
                  <div>จัดวันที่ : {{clubEventDate}}</div>
                  <div>จำนวนผู้เข้าร่วม : {{clubEventPepleAmount}}</div>
                  <div>สถานะ : {{clubEventStatus}}</div>
                  <div>สถานที่จัดกิจกรรม : {{location}}</div>
                  <div>ผู้ขอจัดกิจกรรม : {{clubHost}}</div>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>

          <!--ปุ่มกด-->
          <div>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="approve" :class="{ red: !valid, green: valid }">อนุมัติ</v-btn>
                <v-btn style="margin-left: 15px;" @click="cancel">ยกเลิกกิจกรรม</v-btn>
              </v-col>
            </v-row>
          </div>
          <v-bottom-navigation color="deep-purple accent-4">
            <v-btn>
              <span>
                <router-link to="/Login">logOut</router-link>
              </span>
            </v-btn>
          </v-bottom-navigation>
          <br />
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common"

export default {
  name: "ClubEvent",
  data() {
    return {
      ClubEvent: [],
      ClubEventID: "",
      valid: false,
      clubName: "",
      clubEventName: "",
      clubEventPepleAmount: "",
      clubEventStatus: "",
      clubEventDate: "",
      location: "",
      clubHost: "",
      isSelected: false,
       saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },
  // ดึงข้อมูลจาก ClubEvent ที่เลือกมาเเสดง

  methods: {
    /* eslint-disable no-console */
    selectClubEvent() {
      this.ClubEvent.forEach(clubEvent => {
        if (clubEvent.clubEventID === this.ClubEventID) {
          this.clubName = clubEvent.clubs.clubName
          this.clubEventName = clubEvent.clubEventName
          this.clubEventPepleAmount = clubEvent.clubEventPepleAmount
          this.clubEventStatus = clubEvent.clubEventStatus
          this.location = clubEvent.location.name
          this.clubEventDate = clubEvent.clubEventDate
          this.clubHost = clubEvent.clubHost
          this.isSelected = true
        }
      })
    },
    // ดึงข้อมูล ClubEvent ใส่ combobox
    getClubEvent() {
      http
        .get("/ClubEvent")
        .then(response => {
          this.ClubEvent = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    cancel() {
      if (this.clubEventID === "") {
        alert("กรุณาเลือกกิจกรรม")
      } else {
        http
          .delete("/disapprove/" + this.ClubEventID, this.ClubEventID)
          .then(response => {
            if (response.data) {
              this.saveStatus.message = "ไม่สามารถยกเลิกกิจกรรมได้"
              this.saveStatus.isSuccess = true
              setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
              }, 3000)
            } else {
              this.saveStatus.message = "ยกเลิกกิจกรรมสำเร็จ"
              this.saveStatus.isSuccess = true
              setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
              }, 3000)
              this.getClubEvent()
            }
          })
          .catch(() => {
              this.saveStatus.message = "กรุณาเลือกกิจกรรม"
              this.saveStatus.isSuccess = true
              setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
              }, 3000)
          })
        this.ClubEventID = ""
        this.clubName = ""
        this.clubEventName = ""
        this.clubEventPepleAmount = ""
        this.clubEventStatus = ""
        this.location = ""
        this.clubEventDate = ""
        this.clubHost = ""
        this.isSelected = ""
      }
    },
    // เมื่อกดปุ่ม อนุมัติ ให้ update กิจกรรมที่เลือก ตรง ClubEventStatus จาก รออนุมัติเป็นอนุมัติ
    approve() {
      if (this.clubEventID === "") {
              this.saveStatus.message = "กรุณาเลือกกิจกรรม"
              this.saveStatus.isSuccess = true
              setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
              }, 3000)
      } else {
        http
          .post("/approveevent/" + this.ClubEventID, this.ClubEvent)
          .then(response => {
            this.ClubEventID = ""
            this.clubName = ""
            this.clubEventName = ""
            this.clubEventPepleAmount = ""
            this.clubEventStatus = ""
            this.location = ""
            this.clubEventDate = ""
            this.clubHost = ""
            this.isSelected = ""
            if (response.data) {
               this.saveStatus.message = "อนุมัติกิจกรรมสำเร็จ"
            this.saveStatus.isSuccess = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
            }, 3000)
            }
            // this.$router.push("/view");
          })
          .catch(() => {
          this.saveStatus.message = "กรุณาเลือกกิจกรรม"
          this.saveStatus.isFail = true
           setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 8080)
        })
        this.submitted = true
      }
    },

    // เมื่อกดปุ่ม ยกเลิกกิจกรรม ให้ ลบ กิจกรรม
    clear() {
      this.$refs.form.reset()
    },

    refreshList() {
      this.getClubEvent()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getClubEvent()
  }
}
</script>
