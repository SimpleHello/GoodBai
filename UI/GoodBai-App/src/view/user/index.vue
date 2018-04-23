<template>
<div class="container">
	<topComponent title='数据查询' :showLeft='false'></topComponent>
	<dl class="userInfo">
		<dt>
			<p class="user-logo"></p>
			<p class="user-tel">Hello world</p>
			<p class="user-tag">
				<span class="mLevel">{{data.day}}</span>
				<span class="mName">{{data.hour}}</span>
			</p>
		</dt>
		<dd>总推荐:<span>{{data.id}}</span></dd>
		<dd  @click="$router.push('/share/detail/add')">新增推荐:<span>{{data.pop}}</span></dd>
	</dl>
	<ul class="listCom list-arrow list-icon mt20">
		<listComponent v-for='item in items' @click="$router.push(item.push)" :class='item.cls' :title='item.tit'></listComponent>
	</ul>
	<!--<footComponent :idx='2'></footComponent>-->
</div>
</template>
<script type="text/ecmascript-6">

	import ajax from '../../config/ajax.js';

	export default {
	    data () {
	        return {
	        	data:{},
	        	items:[
					{cls:"icon-jiangli",	push:"/share/detail/ga/10",	tit:"最近10天统计"},
					{cls:"icon-jiangli",	push:"/share/detail/ga/5",	tit:"最近5天统计"},
					{cls:"icon-jiangli",	push:"/share/detail/ga/3",		tit:"最近3天统计"},
	        		{cls:"icon-jiangli",	push:"/share/detail",	tit:"最近推荐"}
	        	]
	        }
	    },
	    methods :{
	        loginOut(){
	        	this.$router.push('/login');	//暂时不做处理只去登录页面
	        },
	        
	    },
		created(){
			this.callDialog('获取列表失败');
	    	document.body.scrollTop = 0;
			ajax.post('/share/getDetailIndex.do').then(data => {
				if(data.error<0){
				this.callDialog('获取列表失败');
				return false;
			}else{
				this.data = data.rows;
				return true
			}
		}).catch(function (err) {
				return err;
			});

	    }
	}
</script>