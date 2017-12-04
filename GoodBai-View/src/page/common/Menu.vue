<template>
  <div>
    <div class="layout">
      <div class="layout-header">
        <div style="height: 10px;width: 100%"></div>
          <div v-show="!loginEn" >
            <Row>
              <Col span="4"><span class="span-font">欢迎 ： {{username}}  登录</span></Col>
              <Col span="4" >
                <Button class="showLogin" type="success" @click="loginOut">注销</Button>
              </Col>
            </Row>
          </div>
        <div v-show="loginEn" >
           <span class="showLogin" style="top:10px">
            <Input  v-model="username" placeholder="用户名" style="width: 150px"></Input>
            <Input  v-model="password" placeholder="密码" style="width: 150px"></Input>
            <Button type="success" @click="login">登录</Button>
          </span>
        </div>

      </div>
    </div>
      <div v-if="leftNav.length>0">
        <div class="layout">
          <div class="layout-slider">
            <Row type="flex">
              <i-col span="24" class="">
                  <Menu theme="dark" width="auto">
                    <div class="layout-logo-left"></div>
                    <Submenu :name="left.name" v-for="(left,$index) in leftNav" :key="left.name">
                      <template slot="title">
                        <Icon type="ios-navigate"></Icon>
                        {{left.descript}}
                      </template>
                      <Menu-item :name="c_node.name" v-for="(c_node,$index) in left.children"
                                 @click.native="addTabNav(c_node,$index)" :key="c_node.nodeId">
                        {{c_node.descript}}
                      </Menu-item>
                    </Submenu>
                  </Menu>
              </i-col>
            </Row>
          </div>
          <div class="layout-body">
            <div class="layout-tab">
              <Row type="flex">
                <i-col span="24">
                  <ul class="layout-tab-title">
                    <li v-for="(tabs,$index) in headTabs" :class="{'layout-tab-actived' : tabs.select}" >
                      <span @click="tabClick(tabs,$index)">{{tabs.descript}}</span>
                      <Icon type="close-circled"></Icon>
                    </li>

                  </ul>
                </i-col>
              </Row>
            </div>
            <div class="layout-content">
              <Row type="flex">
                <i-col span="24">
                  <keep-alive>
                    <router-view></router-view>
                  </keep-alive>
                </i-col>
              </Row>
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <v-error></v-error>
      </div>
  </div>

</template>
<style>
  @import '../../style/head.css';
</style>
<script>
  import Bus from '../../module/eventBus.js';
  import vError from '../404.vue';
  import ajax from '../../module/ajax.js';
  import {ymzs} from '../../module/leftNav.js';
  import msg from 'iview/src/components/message';
  export default {
    data: function () {
      return {
        leftNav:[],
        headTabs:[],
        active:true,
        test:'p',
        loginEn:true,
        username:"",
        password:""
      }
    },
    methods: {
      login(){
        let param = new Object();
        param.name = this.username;
        param.password = this.password;
        ajax.post('/user/loginsubmit.do',param).then(data => {
          if(data.error<0){
            msg.error('获取列表失败', 3);
            return false;
         }else{
            this.leftNav= data.rows;
            ymzs.nav = data.rows;
            this.loginEn=false;
            return true
         }
        }).catch(function (err) {
          return err;
        });
      },
      loginOut(){
            this.leftNav=[];
            this.headTabs=[];
            this.active=true;
            this.loginEn=true;
            this.username="";
            this.password="";
            this.$router.push("/");
            ymzs.nav = [];
      },
      checkTab(tabId){    //检查tab标签页是否已经选中
          var _Tabs=this['headTabs'];
          for(var i=0;i<_Tabs.length;i++){
              if(_Tabs[i].name==tabId){
                  return false;
              }
          }
        return true;
      },
      changActive(){
           //切换选项卡激活样式
          var _tabs=this.headTabs;
          for(var k=0;k<_tabs.length;k++) {
            _tabs[k].select=false;
          }
      },
      addTabNav(n,i) {
          this.changActive();
          var _select=!n.select;
          n.select=_select;
          if(this.checkTab(n.name)){  //如果导航没激活，添加选项卡
            this['headTabs'].push(n);
          }
          this.$router.push(n.url);
      },
      tabClick(n,i){
           this.changActive();
           console.log(" 点击了2:"+ n.url);
           this.$router.push(n.url);
           this.headTabs[i].select=!this.headTabs[i].select
      },
      tabDelClick(){
       alert("123123");
        console.log(" 点击了22222:"+ n.url);
      },
      hash(e){
        return this.$route.path.replace('/','');
      }
    },
    components:{
      "v-error":vError
      }
  }
</script>

