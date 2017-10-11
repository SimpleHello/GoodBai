<template>
  <div class="layout">
    <Row>
      <Col span="12" class="layout-menu-left">
         <Card style="width:100%">
           <p slot="title">
             角色列表
           </p>
         <Table :columns="columns1"
                :data="data1"
                :highlight-row=true
                height="600"
                @on-row-click="clickRow"
                ></Table>
        </Card>
      </Col>
      <Col span="5" offset="1">
        <Card style="width:100%;height:600px">
          <p slot="title">
            权限列表
          </p>
          <Tree1 ref='Tree12' :key="1" :checkIds="checkIds"></Tree1>
        </Card>
      </Col>
    </Row>
  </div>
</template>
<style scoped>
  .ivu-table-body{
    height: 800px;
  }
</style>
<script>
  import vueZtree from '../../components/vue-ztree.vue'
  import Tree1 from '../test/Tree1'

  export default {
    data () {
      return {
        checkIds:0,
        roleName:'',
        ztreeDataSource:[],
        columns1: [
          {
            title: '姓名',
            key: 'name'
          },
          {
            title: '年龄',
            key: 'age'
          },
          {
            title: '地址',
            key: 'address'
          }
        ],
        data1: [
          {
            name: '王小明',
            age: 18,
            address: '北京市朝阳区芍药居'
          },
          {
            name: '张小刚',
            age: 25,
            address: '北京市海淀区西二旗'
          },
          {
            name: '李小红',
            age: 30,
            address: '上海市浦东新区世纪大道'
          },
          {
            name: '周小伟',
            age: 26,
            address: '深圳市南山区深南大道'
          }
        ]
      }
    },
    methods:{
      clickRow(data){
        if(data.name=="王小明"){
            this.checkIds = 1;
        }else{
            this.checkIds = 2;
        }
        var _this=this;
        _this.$refs.Tree12.roleInfoData(this.checkIds,data.name);
      }
    },
  mounted (){
    // 异步获取数据操作
      this.ztreeDataSource = [{
        id:220,
        parentId:0,
        name:"游戏1",
        children:[{
          id:221,
          parentId:220,
          name:"游戏2",
          path:"",
          children:[{
            id:222,
            parentId:221,
            name:"游戏3",
            path:"",
            children:[{
              id:223,
              parentId:222,
              name:"游戏4",
              path:"",
              children:[{
                id:224,
                parentId:223,
                name:"游戏5",
                path:"",
                children:[{
                  id:225,
                  parentId:224,
                  name:"游戏6",
                  path:"",
                  children:[{
                    id:226,
                    parentId:224,
                    name:"游戏末节点",
                    path:""
                  }],
                }],
              }],
            }],
          }],
        }],
        path:"http://www.baidu.com"
      },{
        id:2,
        parentId:0,
        name:"视频",
        children:[{
          id:3,
          parentId:2,
          name:"电影",
          children:[{
            id:4,
            parentId:3,
            name:"国产电影",
            path:""
          },{
            id:5,
            parentId:3,
            name:"好莱坞电影",
            path:""
          },{
            id:6,
            parentId:3,
            name:"小语种电影",
            path:""
          }]
        },{
          id:7,
          parentId:2,
          name:"短片",
          children:[{
            id:9,
            parentId:7,
            name:"电视剧",
            path:""
          },{
            id:10,
            parentId:7,
            name:"短片1",
            path:""
          }]
        }],
        path:""
      }]
  },
   components:{
      vueZtree,
      Tree1
    },
  }
</script>
