<template>
	<ul class="borrowList">
		<li v-for='data in items'>
			<p class="fz26 w60P" @click='goChart(data.code,data.share)'>名称:<span>{{data.share}}</span></p>
			<p @click='goChart(data.code,data.share)'>编码:<span class="blue">{{data.code}}</span></p>
			<p class="w60P col6">类型:<span>{{data.name}}</span></p>
			<p class="col6" @click='goDetail(data.code,data.noDay)'>推荐次数:<span>{{data.num}}次</span></p>
		</li>
	</ul>
</template>
<script>
	export default {
	    props:['items'],
	    methods:{
			goChart(code,name){
				//根据转入id跳转到相应的详情页面
				this.$router.push('/share/detail/chart/'+code+"/"+name);
			},
			goDetail(code,noDay){
				//根据转入id跳转到相应的详情页面
				this.$router.push('/share/detail/info/'+code+"/"+noDay);
			}
	    }
	}
</script>