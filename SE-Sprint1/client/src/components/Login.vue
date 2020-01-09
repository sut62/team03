<template>
  <v-container>
    <br />
    <br />
    <br />
    <v-card max-width="700" class="mx-auto">
      <v-system-bar color="#34ebeb"></v-system-bar>
      <v-system-bar color="#34c6eb"></v-system-bar>
      <v-row justify="center">
        <v-toolbar-title >
          <h1>Employee Login</h1>
        </v-toolbar-title>
      </v-row>

      <v-row justify="center" >
        <v-col cols="5">
          <v-text-field
            solo
            label="Username"
            v-model="user"
            :rules="[(v) => !!v || 'This field is required']"
            required
            clearable
            prepend-icon="mdi-account"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="5">
          <v-text-field
            solo
            label="PASSWORD"
            v-model="pass"
            type="password"
            prepend-icon="mdi-lock"
            required
            counter
            clearable
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-btn @click="Login">Login</v-btn>
      </v-row>

      <br />
      <br />
      <v-system-bar color="#34c6eb"></v-system-bar>
      <v-system-bar color="#34ebeb"></v-system-bar>
    </v-card>
  </v-container>
</template>



<script>
import http from "../http-common"

export default {
  name: "login",
  data() {
    return {
      user: "",
      pass: "",
      redirect: [
        { path: "/Club" },
        { path: "/Budget" },
        { path: "/ClubEventUI" },
        { path: "/EquipmentRental" },
        { path: "/Booking" }
      ]
    }
  },
  methods: {
    /* eslint-enable no-console */
    Login() {
      http
        .get("/check/" + this.user + "/" + this.pass)
        .then(response => {
          // eslint-disable-next-line no-console
          console.log(response)
          if (response) {
            this.$router.push(this.redirect[response.data.officerStatus - 1].path)
          } else {
            alert("ไม่ถูกต้อง")
          }
        })
        .catch(e => {
          // eslint-disable-next-line no-console
          console.log(e)
        })
      this.submitted = true
    },
    refreshList() {}
    /* eslint-enable no-console */
  },
  mounted() {}
}
</script>