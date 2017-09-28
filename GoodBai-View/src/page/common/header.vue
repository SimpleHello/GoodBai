<template>
  <div class="layout">
      <div class="layout-header">
          <div style="height: 10px;width: 100%"></div>
          <Alert type="success" v-show="!loginEn" class="showLogin width150">
            <span class="span-font">欢迎 ： {{name}}</span>
          </Alert>
        <span class="showLogin" style="top:10px">
          <Input v-show="loginEn" v-model="name" placeholder="用户名" style="width: 150px"></Input>
          <Input v-show="loginEn" v-model="password" placeholder="密码" style="width: 150px"></Input>
          <Button v-show="loginEn" type="success" @click="doClick">登录</Button>
        </span>
      </div>
  </div>
</template>
<style>
  @import '../../style/head.css';
</style>
<script>
  import Bus from '@/module/eventBus.js';
  import ajax from '@/module/ajax.js';
  export default {
    data(){
        return{
          loginEn:true,
          name:"",
          password:""
        }
    },
    methods:{
      doClick(){
        console.log(this.name+"  "+this.password);
        let param = new Object();
        param.name = this.name;
        param.password = this.password;
        ajax.post('/login/loginsubmit.do',param).then(function (data) {
          console.log("得到数据："+data);
          return true;
        }).catch(function (err) {
          return err;
        });
      }
    },
    props:['logo']
  }
</script>
