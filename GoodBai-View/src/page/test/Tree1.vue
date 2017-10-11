<template>
   <div>
    <Button type="success" @click="saveRoleFun">新增</Button>
    <Button type="success" @click="cancelRoleFun">还原</Button><hr style="margin-top:10px;margin-bottom: 10px">
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

    const Tree1 = {
        name: 'Tree1',
        data: function () {
            return {
                treeData: [
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
                                nodeSelectNotAll: false,//新增参数，表示父框可以半钩状态
                                parentId: 1,
                                visible: true,
                                searched: false
                            },
                            {
                                id: 1002,
                                name: '2级节点',
                                open: true,
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
                ],
              computed:{
                treeData:function(){
                  if(this.checkIds==1){

                  }else if(this.checkIds==2){

                  }else{
                    return this.treeData;
                  }
                }
               },
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
              alert("新增按钮");
            },
           cancelRoleFun(){
             alert("还原");
           },
        },
      props:['checkIds']
    }

    export default Tree1;
</script>

<style scoped>

</style>
