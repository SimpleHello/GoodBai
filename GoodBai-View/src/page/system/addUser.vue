<template>
<Modal
  title="新增用户"
  :value="addUserTempModel"
  @on-ok="addUserSubmit"
  @on-cancel="addUserCancel"
  ok-text="保存">
  <Form :model="formItem" :label-width="80"  ref="formValidate">
    <FormItem label="姓名:">
      <Input v-model="formItem.name" placeholder="请输入"></Input>
    </FormItem>
    <FormItem label="密码:">
      <Input v-model="formItem.password" placeholder="请输入"></Input>
    </FormItem>
    <FormItem label="电话号码:">
      <Input v-model="formItem.tel" placeholder="请输入"></Input>
    </FormItem>
    <FormItem label="性别:">
      <RadioGroup v-model="formItem.sex">
        <Radio label="1">男</Radio>
        <Radio label="0">女</Radio>
      </RadioGroup>
    </FormItem>
    <FormItem label="权限分组" prop="auth">
      <Select v-model="formItem.auth" placeholder="请选择用户权限" :multiple="true">
        <Option v-for="item in auth" :value="item.id" :key="item.id">{{item.name}}</Option>
      </Select>
    </FormItem>
    <FormItem label="启用:">
      <i-switch v-model="formItem.enable" size="large">
        <span slot="open">开启</span>
        <span slot="close">关闭</span>
      </i-switch>
    </FormItem>
  </Form>
  </Modal>
</template>
<script>
  import ajax from '../../module/ajax.js';
  import msg from 'iview/src/components/message';
  export default{
    data () {
      return {
        auth:[],
        formItem: {
          name: '',
          password: '',
          sex: 'male',
          switch: true,
          auth:[],
          tel:''
        }
      }
    },
    methods:{
      addUserSubmit(){
        let map = this.formItem;
        let requestData ={
          name:map.name,
          password:map.password,
          tel:map.password,
          enable:Number(map.enable?1:0),
          auth:map.auth,
          sex:Number(map.sex)
        };
        ajax.post('/user/saveUser.do',requestData).then(data => {
          msg.error(data.message, 3);
          this.cleanForm();
      }).catch(function (err) {
          return err;
        });

      },
      addUserCancel(){
        this.cleanForm();
      },
      cleanForm(){
        this.formItem={
          name: '',
          password: '',
          sex: 'male',
          switch: true,
          auth:[],
          tel:''
        }
        this.$emit("closeModelEvent","closeModelEvent");
      }
    },
    created(){
      let param = new Object();
      ajax.post('/role/getFunTree.do',param).then(data => {
        if(data.error<0){
            msg.error('获取列表失败', 3);
            return false;
          }else{
            this.auth =data.rows;
            return true
          }
        }).catch(function (err) {
            return err;
          });
    },
    props:['addUserTempModel']
  }
</script>
