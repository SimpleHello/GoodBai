<template>
<Modal
  title="新增用户"
  :value="addUserTempModel"
  @on-ok="addUserSubmit"
  @on-cancel="addUserCancel"
  ok-text="保存">
  <Form :model="formItem" :label-width="80">
    <FormItem label="姓名:">
      <Input v-model="formItem.name" placeholder="请输入"></Input>
    </FormItem>
    <FormItem label="密码:">
      <Input v-model="formItem.password" placeholder="请输入"></Input>
    </FormItem>
    <FormItem label="性别:">
      <RadioGroup v-model="formItem.sex">
        <Radio label="male">男</Radio>
        <Radio label="female">女</Radio>
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

  export default{
    data () {
      return {
        auth:[],
        formItem: {
          name: '',
          password: '',
          sex: 'male',
          switch: true,
          auth:[]
        }
      }
    },
    methods:{
      addUserSubmit(){
        this.$emit("closeModelEvent","closeModelEvent");
      },
      addUserCancel(){
        this.formItem={
              name: '',
              password: '',
              sex: 'male',
              switch: true
          }
        this.$emit("closeModelEvent","closeModelEvent");
      }
    },
    created(){
      let param = new Object();
      ajax.post('/role/getList.do',param).then(data => {
        if(data.error<0){
        msg.error('获取列表失败', 3);
        return false;
      }else{
        this.auth =data.rows;
        console.log(this.auth);
        return true
      }
    }).catch(function (err) {
        return err;
      });
    },
    props:['addUserTempModel']
  }
</script>
