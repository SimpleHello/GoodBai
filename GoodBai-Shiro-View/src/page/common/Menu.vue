<template>
  <div class="layout">
    <div class="layout-slider">
      <Row type="flex">
        <i-col span="24" class="">
          <Menu :active-name="leftNav[0].childrens[0].nodeId"   theme="dark" width="auto" :open-names="[]">
            <div class="layout-logo-left"></div>
            <Submenu :name="left.nodeId" v-for="(left,$index) in leftNav" :key="left.nodeId">
              <template slot="title">
                <Icon type="ios-navigate"></Icon>
                {{left.nodeName}}
              </template>
              <Menu-item :name="c_node.nodeId" v-for="(c_node,$index) in left.childrens"
                         @click.native="addTabNav(c_node,$index)" :key="c_node.nodeId">{{c_node.nodeName}}
                <!--<router-link :to="c_node.reflink">{{c_node.nodeName}}</router-link>-->
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
                {{tabs.nodeName}}
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
</template>

<script>
  import Bus from '@/module/eventBus.js';
  import {ymzs} from '@/module/leftNav.js';
  export default {
    data: function () {
      return {
        leftNav: ymzs.nav,
        tab0: true,
        tab1: true,
        tab2: true,
        headTabs:[],
        isShow:false,
        active:true,
        test:'p'
      }
    },methods: {
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
      ntoggle(d,i) {
          this.leftNav[i].isActive=!this.leftNav[i].isActive
      },
      tabClick(n,i){
           this.changActive();
           this.$router.push(n.reflink);
           this.headTabs[i].select=!this.headTabs[i].select
      },
      changeSelect(n,i,p){

      },
      hash(e){
        return this.$route.path.replace('/','');
      }
    },
    created(){
//      Bus.$on('getTarget',function(d){
//          console.info(d)
//      })
    },
    beforeMount: function () {
      this['headTabs'].push(this['leftNav'][0].childrens[0]);
    },
    mounted: function () {
        var data=this.leftNav;
    },
    components:{
          'curr-Template': {
            template: '<div style="background: lemonchiffon;position: absolute;top: 0;bottom: 0;left: 0;right: 0;"></div>'
          }
      }
  }
</script>
