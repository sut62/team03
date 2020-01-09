<template>
  <v-app>
      <v-card class="text-center justify-center py-6">
      <h1 class="font-weight-bold display-3 basil--text">ระบบจัดการชมรม</h1>
    </v-card>
    <v-content>
        <router-view ></router-view>
    </v-content>
  </v-app>
</template>
<script>
export default {
    name:'app',
    components:{}
}
</script>
<style>
</style>
