<!--主页面组件-->
<template>
  <div class="warpper">
    <v-head :logo="test"></v-head>
    <v-Menu></v-Menu>
  </div>
</template>
<script>
  import vHead from './common/header.vue';
  import vMenu from './common/Menu.vue';
  export default {
    data(){
      return {
        test:''
      }
    },
    components:{
      vHead,vMenu
    },
    created(){
//        console.info('home load');
    }
  }
</script>
