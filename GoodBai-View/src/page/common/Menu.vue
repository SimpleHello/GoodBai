<template>
  <div>
    <div class="layout">
      <div class="layout-header">
        <div style="height: 10px;width: 100%"></div>
          <div v-show="!loginEn" >
            <Alert type="success" class="showLogin width250">
              <span class="span-font">欢迎 ： {{name}}  登录</span>
            </Alert>
            <Button class="showLogin" type="success" @click="loginOut">注销</Button>
          </div>
        <div v-show="loginEn" >
           <span class="showLogin" style="top:10px">
            <Input  v-model="name" placeholder="用户名" style="width: 150px"></Input>
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
                  <Menu  :active-name="leftNav[0].children[0].name"   theme="dark" width="auto" :open-names="[]">
                    <div class="layout-logo-left"></div>
                    <Submenu :name="left.name" v-for="(left,$index) in leftNav" :key="left.name">
                      <template slot="title">
                        <Icon type="ios-navigate"></Icon>
                        {{left.descript}}
                      </template>
                      <Menu-item :name="c_node.name" v-for="(c_node,$index) in left.children"
                                 @click.native="addTabNav(c_node,$index)" :key="c_node.nodeId">{{c_node.descript}}
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
                    <li v-for="(tabs,$index) in headTabs" :class="{'layout-tab-actived' : tabs.select}" @click="tabClick(tabs,$index)">
                      {{tabs.descript}}
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
        isShow:false,
        active:true,
        test:'p',
        loginEn:true,
        name:"",
        password:""
      }
    },
    methods: {
      login(){
        console.log(this.name+"  "+this.password);
        let param = new Object();
        param.name = this.name;
        param.password = this.password;
        ajax.post('/login/loginsubmit.do',param).then(data => {
          if(data.error<0){
            msg.error('获取列表失败', 3);
            return false;
         }else{
            this.leftNav= data.rows;
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
            this.isShow=false;
            this.active=true;
            this.loginEn=true;
            this.name="";
            this.password="";
      },
      checkTab(tabId){    //检查tab标签页是否已经选中
          var _Tabs=this['headTabs'];
          for(var i=0;i<_Tabs.length;i++){
              if(_Tabs[i].nodeId==tabId){
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
          if(this.checkTab(n.nodeId)){  //如果导航没激活，添加选项卡
            this['headTabs'].push(n);
          }
          this.$router.push(n.reflink);
      },
      tabClick(n,i){
           this.changActive();
           this.$router.push(n.reflink);
           this.headTabs[i].select=!this.headTabs[i].select
      },
      hash(e){
        return this.$route.path.replace('/','');
      }
    },
    computed:{

    },
    components:{
      "v-error":vError
      }
  }
</script>

