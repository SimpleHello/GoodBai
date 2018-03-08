<template>
   <div>
     <div>
       <Row>
         <Col span="12">
           <p style="font-size: 14px;font-weight: 900">角色: {{roleName}}</p>
         </Col>
         <Col span="12">
           <Button type="success" @click="saveRoleFun">新增</Button>
           <Button type="success" @click="cancelRoleFun">返回</Button>
         </Col>
       </Row>
       <hr style="margin-top:10px;margin-bottom: 10px">
     </div>
    <ZTree
        ref='tree'
        :treeData="treeData"
        :options="options"
        @node-click="itemClick"
        :key="1"

    />
   </div>
</template>

<script>
    import Vue from 'vue';
    import axios from 'axios';
    import {ZTree} from '../../module/tree'
    Vue.use(ZTree);
    const treeValue =  [
      {
        id: 1,
        name: '一级节点',
        open: true,
        checked: false,
        nodeSelectNotAll: false,//新增参数，表示父框可以半钩状态
        parentId: null,
        visible: true,
        searched: false,
        children: [
          {
            id: 1001,
            name: '2级节点',
            open: true,
            checked: false,
            nodeSelectNotAll: false,//新增参数，表示父框可以半钩状态
            parentId: 1,
            visible: true,
            searched: false
          },
          {
            id: 1002,
            name: '2级节点',
            open: true,
            checked: false,
            nodeSelectNotAll: false,//新增参数，表示父框可以半钩状态
            parentId: 1,
            visible: true,
            searched: false
          }
        ]
      },
      {
        id: 2,
        name: '一级节点',
        open: true,
        checked: false,
        nodeSelectNotAll: false,
        parentId: null,
        visible: true,
        searched: false
      },
      {
        id: 3,
        name: '一级节点',
        open: true,
        checked: false,
        nodeSelectNotAll: false,
        parentId: null,
        visible: true,
        searched: false
      }
    ];
    const Tree1 = {
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
                console.log(node.id);
            },
            saveRoleFun(){
              var _this=this;
              let nodes = _this.$refs.tree.getSelectedNodeIds();
              console.log(nodes);//选中的值
            },
           cancelRoleFun(){
             alert("还原");
           },
           roleInfoData(checkIds,roleName){
             let data = treeValue;
             var recurFunc = (datax,arr) => {
               datax.forEach((i)=>{
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
             if(checkIds==1){
               recurFunc(data,[1001,1002]);
             }else if(checkIds==2){
               recurFunc(data,[3]);
             }
             this.roleName = roleName;
             this.treeData = data;
           }
        }
    }

    export default Tree1;
</script>

<style scoped>

</style>
