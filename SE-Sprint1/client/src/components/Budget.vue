<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ระบบจัดการงบชมรม</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row>
            <v-col cols="12">
              <v-select
                label="Club"
                outlined
                v-model="Budget.clubId"
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
              <v-select
                label="Club Type"
                outlined
                v-model="Budget.clubtypeId"
                :items="ClubType"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

       

           <v-row>
            <v-col cols="12" sm="12" md="12">
              <v-text-field label="Budget" v-model="amount" outlined></v-text-field>
            </v-col>
          </v-row>
     
            <v-row>
            <v-col cols="12">
              <v-select
                label="Member Name"
                outlined
                v-model="Budget.memberId"
                :items="Clubmember"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12">
              <v-btn @click="savebudget" :class="{ red: !valid, green: valid }">submit</v-btn>
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
import http from "../http-common";

export default {
  name: "Budget",
  data() {

    return {
    
     Budget: {
        clubId: "",
        clubtypeId: "",  
        memberId: "",
        amount:""
      },
      ClubType: [],
      Clubmember: [],
      Clubs: [],
      valid: false,
      foo: 0,
      amount: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    savebudget() {
    // @PostMapping("/budget/{amount}/{clubId}/{clubtypeId}/{memberId}")
      http
        .post(
          "/budget/" +
            this.amount +
            "/" +
            this.Budget.clubId +
            "/" +
            this.Budget.clubtypeId +
            "/" +
            this.Budget.memberId,
          this.budget
        )
        .then(response => {
          console.log(response.data);
          this.amount = "";
          this.Budget.clubId = "";
          this.Budget.clubtypeId = "";
          this.Budget.memberId = "";
          if (response) {
            alert("ทำรายการสำเร็จ");
          }
          // this.$router.push("/view");
        })
        .catch(e => {
          alert('ไม่สามารถทำรายการได้');
          console.log(e);
        });
      this.submitted = true;
    },


    // ดึงข้อมูล Club ใส่ combobox
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

    // ดึงข้อมูล patient ใส่ combobox
    getClubType() {
    
      http
        .get("/ClubType")
        .then(response => {
          this.ClubType = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
   
    // ดึงข้อมูล Clubmember ใส่ combobox
    getClubmember() {
      http
        .get("/Clubmember")
        .then(response => {
          this.Clubmember = response.data;
          console.log("Clubmember" + response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    
        clear() {
        this.$refs.form.reset();
        
      },
       refreshList() {
         this.getClubs();
         this.getClubType();
         this.getClubmember();
      },
       viewData() {
      this.$router.push("/BudgetData")
    },
      /* eslint-enable no-console */


  },
  mounted() {
    this.getClubs();
    this.getClubType();
    this.getClubmember();
  }
};
</script>