<template>
	<div class="userBorrow">
		<shareTitle :idx='12' :code="code"></shareTitle>
		<shareInfoList v-if='hasBorrow' :items='list'></shareInfoList>
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
				code:'',
	            hasBorrow:true,	//开关
	            hasMore:true,	//开关-提示-->没有更多
	            borrowMsg:'当前没有任何数据',
	            borrowCls:'error-txt',
	            list:[]
	        }
	    },
	    mounted:function(){
			let param = new Object();
			var code = this.$route.params.code;
			var noDay = this.$route.params.noDay;
			param.code = code;
			this.code = code;
			param.noDay = noDay;
			ajax.post('/share/getDetailList.do',param).then(data => {
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