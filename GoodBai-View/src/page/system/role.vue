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
          <roleTree ref='Tree12' :key="1" :checkIds="checkIds"></roleTree>
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
  import roleTree from './roleTree.vue'
  import ajax from '../../module/ajax.js';
  import msg from 'iview/src/components/message';
  import {formatDateLong} from '../../module/DateUtil.js'
  var column = [
    {
      titile:'id',
      key:'id'
    },
    {
      title: '角色名',
      key: 'name'
    },
    {
      title: '创建时间',
      key: 'ctime',
      render: (h, params) => {
        return formatDateLong(params.row.ctime,'yyyy-MM-dd hh:mm');
      }
    }
  ];
  export default {
    data () {
      return {
        checkIds:0,
        roleName:'',
        ztreeDataSource:[],
        columns1: column,
        data1: []
      }
    },
    methods:{
      clickRow(data){
        this.checkIds = data.id;
        var _this=this;
        _this.$refs.Tree12.roleInfoData(this.checkIds,data.name);
      }
    },
  mounted (){
    // 异步获取数据操作
      let param = new Object();
      ajax.post('/role/getList.do',param).then(data => {
        if(data.error<0){
        msg.error('获取列表失败', 3);
        return false;
      }else{
        this.data1 =data.rows;
        return true
      }
      }).catch(function (err) {
        return err;
      });
      },
   components:{
      vueZtree,
      roleTree
    },
  }
</script>
