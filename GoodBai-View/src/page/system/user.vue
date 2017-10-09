<template>
  <div>
    <Form :model="formItem" :label-width="80">
      <Row>
        <i-col span="4">
          <Form-item label="姓名">
            <Input v-model="formItem.input" placeholder="请输入"></Input>
          </Form-item>
        </i-col>
        <i-col span="3">
          <Form-item label="性别">
            <Select v-model="formItem.select" placeholder="请选择">
              <Option value="1">男</Option>
              <Option value="0">女</Option>
            </Select>
          </Form-item>
        </i-col>
        <i-col span="3" style="margin-left: 5px">
            <Button type="success">查询</Button>
            <Button type="success" @click="addUserModel = true">新增</Button>
        </i-col>
      </Row>
    </Form>
    </br>
    <Row>
      <Table border :context="self" :columns="columns4" :data="data1"></Table>
    </Row>
    <v-addUser :addUserTempModel="addUserModel" v-on:closeModelEvent="closeAddUser"></v-addUser>
  </div>
</template>
<script>
  import addUser from './addUser.vue';

  export default {
    data () {
      return {
        addUserModel:false,
        self:this,
        flag:'lihoo',
        formItem: {
          input: '',
          select: '',
          switch: true,
          slider: [20, 50]
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
            key: 'sex'
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
                      console.log(params)
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
                        console.log(params)
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
        data1: [{name:"123",sex:"男",tel:"110110110"}]
      }
    },
    methods:{
      show(i){
        debugger;
        alert('sdsd')
      },
      closeAddUser(data){
         console.log(data);
         this.addUserModel=false;
      },
      cc(name){
        debugger;
        alert(1)
      }
    },
    components:{
      "v-addUser":addUser
    }

  }
</script>
