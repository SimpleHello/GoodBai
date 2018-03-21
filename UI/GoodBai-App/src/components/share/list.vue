<template>
	<ul class="borrowList">
		<li v-for='data in items' @click='goDetail(data.id)'>
			<p class="fz26 w60P">名称:<span>{{data.share}}</span></p>
			<p>编码:<span class="blue">{{data.code}}</span></p>
			<p class="w60P col6">当前价格/涨幅:<span>{{data.noEnd}}#{{data.amm}}</span></p>
			<p class="col6">类型:<span>{{data.name}}</span></p>
			<p class="w60P col6">已经删除:<span>{{data.deltype}}</span></p>
			<p class="col6">是否新增:<span>{{data.addtype}}天</span></p>
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