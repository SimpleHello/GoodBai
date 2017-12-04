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
     <div class="tree-box" style="height:550px;position:relative;overflow-y:auto">
       <div class="zTreeDemoBackground left">
         <ul id="roleTree" class="ztree"></ul>
       </div>
     </div>
   </div>
</template>

<script>
    import Vue from 'vue';
    import ajax from '../../module/ajax.js';
    import msg from 'iview/src/components/message';
    var roleId = 0;
    var treeValue = [];
    const roleTree = {
      name: 'Tree1',
      data:function(){
        return{
          roleName:"",
          setting:{
            check: {
              enable: true,
              nocheckInherit: false
            },
            view: {
              showIcon: true
            },
            data: {
              simpleData: {
                enable: true
              }
            },
            callback:{
              onClick:this.itemClick
            }
          },
          zNodes:treeValue
        }
      },
      methods: {
            itemClick (event, treeId, treeNode, clickFlag) {
              console.log(treeNode);
              alert("选择了:"+treeId);
            },
            saveRoleFun(){
              var _this=this;
              let nodes = _this.$refs.roleTree.getSelectedNodeIds();
              msg.info(nodes+"  >> roleId:"+roleId);//选中的值
            },
            cancelRoleFun(){
              this.initDate();
            },
            checkNodeByCodes: function (treeId, codes) {
              var treeObj = $.fn.zTree.getZTreeObj(treeId);
              treeObj.checkAllNodes(false);
              if(codes === null || codes.length === 0){
                return;
              }
              var nodes = treeObj.getNodesByFilter(function (node) {
                return codes.containsObj(node);
              }, false);
              nodes.forEach(function (node) {
                treeObj.checkNode(node, true, true, true);
              });
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
                 $.fn.zTree.init($("#roleTree"), this.setting,data.rows);
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

<style>
  @import url('../../zTree/css/zTreeStyle/zTreeStyle.css');
</style>
