<template>
	<ul class="borrowList">
		<li v-for='data in items' @click='goDetail(data.id)'>
			<p class="fz26 w60P">名称:<span>{{data.share}}</span></p>
			<p>编码:<span class="blue">{{data.code}}</span></p>
			<p class="w60P col6">类型:<span>{{data.name}}</span></p>
			<p class="col6">推荐次数:<span>{{data.num}}次</span></p>
		</li>
	</ul>
</template>
<script>
	export default {
	    props:['items'],
	    methods:{
	    	goDetail(id){
	    		//根据转入id跳转到相应的详情页面
//	    		this.$router.push('/user/borrowDetail/'+id);
	    	}
	    }
	}
</script>