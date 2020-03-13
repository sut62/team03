<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 cyan darken-3 text-center"
      >เลือกระบบ</h2>
    </v-flex>
        <br />
        <br />
        <br />
              <v-row justify="center">
        <v-btn style="margin-left: 15px;" color="cyan lighten-4"  v-on:click="Club">ระบบลงทะเบียนชมรม</v-btn>
      </v-row>
       <br />
       <br />
       <br />
           <v-row justify="center">
          <v-btn style="margin-left: 15px;" color="light-blue lighten-3"  v-on:click="Clubmember">ระบบเพิ่มสมาชิก</v-btn>
         </v-row> 
  </v-container>
</template>

<script>


export default {
  name: "ClubHome",
  data() {
    return {
     
      redirect: [
        { path: "/Club" },
        { path: "/Clubmember" }
        
      ]
    }
  },
  methods: {
    /* eslint-enable no-console */
   Club() {
      this.$router.push("/Club")
    },
      Clubmember() {
      this.$router.push("/Clubmember")
    },
    refreshList() {}
    /* eslint-enable no-console */
  },

}
</script>