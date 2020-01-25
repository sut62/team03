import Vue from "vue";
import Router from "vue-router";
import Home from "../components/Home";
import ClubEventUI from "../components/ClubEventUI";
import Login from "../components/Login";
import Club from "../components/Club";
import Booking from "../components/Booking";
import Budget from "../components/Budget";
import EquipmentRental from "../components/EquipmentRental";
import ClubEventStatusUI from "../components/ClubEventStatusUI";
import ClubEventHome from "../components/ClubEventHome";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    { path: "/", component: Login },
    { path: "/home", component: Home },
    { path: "/ClubEventUI", component: ClubEventUI },
    { path: "/Login", component: Login },
    { path: "/Club", component: Club },
    { path: "/Booking", component: Booking },
    { path: "/Budget", component: Budget },
    { path: "/EquipmentRental", component: EquipmentRental },
    { path: "/ClubEventStatusUI", component: ClubEventStatusUI },
    { path: "/ClubEventHome", component: ClubEventHome }
    
  ]

});
