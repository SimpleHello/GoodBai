<template>
	<ul class="borrowList">
		<li v-for='data in items'>
			<p class="fz26 w60P"  @click='goChart(data.code,data.share)'>名称:
				<span>{{data.share}}
					<span v-if="data.addtype==1" class="mName" style="color: #00B83F">(增)</span>
					<span v-if="data.deltype==-1" style="color:#b8231c">(删)</span>
				</span>
			</p>
			<p  @click='goChart(data.code,data.share)'>编码:<span class="blue">{{data.code}}</span></p>
			<p class="w60P col6">当前价格:<span>{{data.noEnd}}</span></p>
			<p class="col6">类型:<span>{{data.name}}</span></p>
			<p class="w60P col6">涨幅:<span>{{data.amm}}</span></p>
			<p class="col6">时间:<span>{{data.noDay}}-{{data.noHour}}</span></p>
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
	    	}
	    }
	}
</script>