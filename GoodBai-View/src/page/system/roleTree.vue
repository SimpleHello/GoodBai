<template>
   <div>
     <div>
       <Row>
         <i-col span="12">
           <p style="font-size: 14px;font-weight: 900">角色: {{roleName}}</p>
         </i-col>
         <i-col span="12">
           <Button type="success" @click="saveRoleFun">保存</Button>
           <Button type="success" @click="cancelRoleFun">清空</Button>
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

<script type="text/ecmascript-6">
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
//              onClick:this.itemClick
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
              var treeObj = $.fn.zTree.getZTreeObj("roleTree");
              var nodes =treeObj.getCheckedNodes(true);
              var selectIds = new Array();
              for(var i=0;i<nodes.length;i++){
                if(nodes[i].id==0){
                  continue;
                }
                selectIds.push(nodes[i].id);
              }
              msg.success("  >> roleId:"+roleId+" 选中的树ID值:"+selectIds);//选中的值
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
                return codes.includes(node.id);
              }, false);
              debugger;
              nodes.forEach(function (node) {
                treeObj.checkNode(node, true, true, true);
              });
            },
           roleInfoData(checkIds,roleName){
             roleId = checkIds;
             let parms = {"id":checkIds};
             console.log("点击了角色的ID:"+checkIds);
             ajax.post('/role/getFunByRoleId.do',parms).then(data => {
               if(data.error<0){
                 msg.error('获取列表失败', 3);
                 return false;
               }else{
                  console.log("获取 角色的ID:"+checkIds+"的信息成功!");
                   let arr = data.rows;
                   this.checkNodeByCodes("roleTree",arr);
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
                roleId = 0;
                this.roleName = "";
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
