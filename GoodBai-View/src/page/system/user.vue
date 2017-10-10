<template>
  <div>
    <Form :model="formItem" :label-width="80">
      <Row>
        <i-col span="4">
          <Form-item label="姓名">
            <Input v-model="formItem.queryName" placeholder="请输入"></Input>
          </Form-item>
        </i-col>
        <i-col span="3">
          <Form-item label="性别">
            <Select v-model="formItem.querySex" placeholder="请选择">
              <Option value="1">男</Option>
              <Option value="0">女</Option>
            </Select>
          </Form-item>
        </i-col>
        <i-col span="3" style="margin-left: 5px">
            <Button type="success" @click="queryUser">查询</Button>
            <Button type="success" @click="addUserModel = true">新增</Button>
        </i-col>
      </Row>
    </Form>
    </br>
    <Row>
      <Table border :context="self" :columns="columns4" :data="data1"></Table>
    </Row>
    <v-addUser :addUserTempModel="addUserModel" v-on:closeModelEvent="closeAddUser"></v-addUser>
    <v-editUser :editUserTempModel="editUserModel" :formItem="editDate" v-on:closeEditModelEvent="closeEditUser"></v-editUser>
  </div>
</template>
<script>
  import addUser from './addUser.vue';
  import editUser from './editUser.vue';
  import msg from 'iview/src/components/message';
  import ajax from '../../module/ajax.js';

  export default {
    data () {
      return {
        addUserModel:false,
        editUserModel:false,
        editDate:{},
        self:this,
        formItem: {
          queryName: '',
          querySex: ''
        },
        columns4: [
          {
            type: 'selection',
            width:60,
            align: 'center'
          },
          {
            title: '姓名',
            key: 'name'
          },
          {
            title: '性别',
            key: 'sex',
            render: (h, params) => {
             if(1==params.row.sex){
                return "男"
              }else{
                return "女"
              }
            }
          },
          {
            title:'联系电话',
            key:  'tel'
          },
          {
            title: '操作',
            key: 'doctor',
            render: (h, params) => {
                return h('div', [
                  h('Button', {
                    props: {
                      type: 'success',
                      size: 'small'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                      this.doShowMsg(params.index)
                  }}
                  }, '查看'),
                  h('Button', {
                    props: {
                      type: 'warning',
                      size: 'small'
                    },
                    style: {
                        marginRight: '5px'
                      },
                    on: {
                      click: () => {
                      this.editUser(params.index)
                      }}
                  }, '编辑'),
                  h('Button', {
                    props: {
                      type: 'error',
                      size: 'small'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                      console.log(params)
                  }}
                  }, '删除')
                ]);
         }
        }
        ],
      data1:[]
      }
    },
    methods:{
      doShowMsg(index) {
        this.$Modal.info({
          title: '用户信息',
          content: '姓名:'+this.data1[index].name+'<br>性别：'+this.data1[index].sex+'<br>电话号码：'+this.data1[index].tel
        })
      },
      editUser(index){
        this.editUserModel = true,
        this.editDate=this.data1[index]
      },
      queryUser(){
        let map = this.formItem;
        let sex = map.querySex;
        if(sex==""){
          sex = -1;
        }else{
          sex = Number(map.querySex);
        }
        let requestData ={
          name:map.queryName,
          sex:sex
        };
        this.getUserList(requestData);
      },
      closeAddUser(data){
         this.addUserModel=false;
         this.queryUser();
      },
      closeEditUser(data){
        this.editUserModel=false;
        this.queryUser();
      },
      cc(name){
        debugger;
        alert(1)
      },
      getUserList(param){
        ajax.post('/user/getUserList.do',param).then(data => {
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
      }
    },
    created(){
        this.getUserList({sex:-1});
    },
    components:{
      "v-addUser":addUser,
      "v-editUser":editUser
    }

  }
</script>
