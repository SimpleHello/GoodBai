<!--主页面组件-->
<template>
  <div class="warpper">
    <!--<v-head :logo="test"></v-head>-->
    <v-Menu></v-Menu>
  </div>
</template>
<script>
  import vMenu from './common/Menu.vue';
  export default {
    data(){
      return {
        test:'123'
      }
    },
    components:{
      "v-Menu":vMenu
    }
  }
</script>
