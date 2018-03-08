<template>
   <div>
     <div>
       <Row>
         <i-col span="12">
           <p style="font-size: 14px;font-weight: 900">角色: {{roleName}}</p>
         </i-col>
         <i-col span="12">
           <Button type="success" @click="saveRoleFun">新增</Button>
           <Button type="success" @click="cancelRoleFun">返回</Button>
           <Button type="success" @click="qingkongRoleFun">清空1</Button>
         </i-col>
       </Row>
       <hr style="margin-top:10px;margin-bottom: 10px">
     </div>
    <ZTree
        ref='roleTree'
        :treeData="treeData"
        :options="options"
        @node-click="itemClick"
        :key="1"

    />
   </div>
</template>

<script type="text/ecmascript-6">
    import Vue from 'vue';
    import ajax from '../../module/ajax.js';
    import msg from 'iview/src/components/message';
    import {ZTree} from '../../module/tree';
    Vue.use(ZTree);
    var roleId = 0;
    var treeValue = [];
    const roleTree = {
        name: 'Tree1',
        data: function () {
            return {
                treeData: treeValue,
                roleName:'',
                options: {
                    labelKey: 'name',
                    showCheckbox: true,
                    halfCheck: true,//控制父框是否需要半钩状态
                    search: {
                        useInitial: true,
                        useEnglish: false,
                        customFilter: null
                    }
                },
            }
        },
      methods: {
            itemClick (node) {
              var _this=this;
              _this.$refs.roleTree.handlecheckedChange(node);
            },
            saveRoleFun(){
              var _this=this;
              let nodes = _this.$refs.roleTree.getSelectedNodeIds();
              msg.info(nodes+"  >> roleId:"+roleId);//选中的值
            },
            cancelRoleFun(){
              this.initDate();
            },
            qingkongRoleFun(){
              debugger;
              var _this=this;
              _this.$refs.roleTree.dealTreeData();
//              this.treeData = treeValue;
            },
           roleInfoData(checkIds,roleName){
//             this.initDate();//初始化一下
             roleId = checkIds;
             this.treeData = treeValue;
             var recurFunc = (treedata,arr) => {
               treedata.forEach((i)=>{
                 console.log(i+"  >> "+i.id+" >> "+arr.indexOf(i.id));
                 if(arr.indexOf(i.id)>=0){
                   i.checked = true;
                 }else {
                  i.checked = false;
                }
               if(i.children && i.children.length>0){
                 recurFunc(i.children,arr);
               }
             });
             }
             let parms = {"id":checkIds};
             console.log("触发时间"+checkIds);
             ajax.post('/role/getFunByRoleId.do',parms).then(data => {
               if(data.error<0){
                 msg.error('获取列表失败', 3);
                 return false;
               }else{
                   let arr = data.rows;
                   recurFunc(this.treeData,arr);
                   this.roleName = roleName;
                   return true;
               }
             }).catch(function (err) {
                 return err;
               });
             return true;
           },
        initDate(){
          ajax.post('/role/getFunTree.do',{}).then(data => {
            if(data.error<0){
            msg.error('获取列表失败', 3);
              }else{
                this.treeData = data.rows;
                treeValue = data.rows;
              }
            }).catch(function (err) {
                return err;
          });
        }
        },
      created(){
          this.initDate();

      }
    }

    export default roleTree;
</script>

<style scoped>

</style>
