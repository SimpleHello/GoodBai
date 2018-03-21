<template>
	<div class="userBorrow">
		<shareTitle :idx='0'></shareTitle>
		<shareList v-if='hasBorrow' :items='list'></shareList>
		<pageError v-if='!hasBorrow' :msg='borrowMsg' :class='borrowCls'></pageError>
		<noMore v-if='!hasMore'></noMore>
	</div>
</template>
<script type="text/ecmascript-6">
	//引入data json
//	import data from '../../data/order/list1.json';
	import ajax from '../../config/ajax.js';
	export default {
	    data () {
	        return {
	            hasBorrow:true,	//开关
	            hasMore:true,	//开关-提示-->没有更多
	            borrowMsg:'当前没有任何数据',
	            borrowCls:'error-txt',
	            list:[]
	        }
	    },
	    mounted:function(){
			ajax.post('/share/getDetail.do').then(data => {
				if(data.error<0){
					this.callDialog('获取列表失败');
					this.hasBorrow = false;
					return false;
				}else{
					this.list = data.rows;
					return true
				}
			}).catch(function (err) {
					return err;
			});
	    }
	}
</script>