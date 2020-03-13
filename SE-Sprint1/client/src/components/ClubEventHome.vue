<template>
  <v-container>

    <v-card-title class="text-center justify-center py-6" style="Bac">
      <h3 class="font-weight-bold  basil--text">เลือกระบบที่ต้องการ</h3>
    </v-card-title>

    <v-row justify="center">
      
            <div>
            <v-btn  color="#0093FFB2" style="margin-left: 15px;" @click="SysteamA" >ระบการจัดกิจกรรมชมรม</v-btn>
            <br/>
            </div>
            <div>
            <v-btn color="#5900FFB3" style="margin-left: 15px;" @click="SysteamB">ระบบอนุมัติการจัดกิจกรรมชมรม</v-btn>
        <!--ปุ่มกด--> 
            </div>
         
       
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "ClubEventHome",
  data() {
    return {
        redirect: [
        { path: "/ClubEventStatusUI" },
        { path: "/ClubEventUI" }
        ]
    }
  },
   
  methods: {
    // เมื่อกดปุ่ม อนุมัติ ให้ update กิจกรรมที่เลือก ตรง ClubEventStatus จาก รออนุมัติเป็นอนุมัติ
    SysteamA() {
      this.$router.push(this.redirect[1].path)
    },
     SysteamB() {
      this.$router.push(this.redirect[0].path)
    },

    refreshList() {
      this.getClubEvent();

     
    }
    /* eslint-enable no-console */
  }
};
</script>
