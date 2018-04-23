<template>
	<ul class="borrowList">
		<li v-for='data in items'>
			<p class="fz26 w60P">名称:
				<span>{{data.share}}
					<span v-if="data.push==1" class="mName" style="color: #00B83F">(增)</span>
					<span v-if="data.pop==1" style="color:#b8231c">(删)</span>
				</span>
			</p>
			<p>编码:<span class="blue">{{data.code}}</span></p>
			<p class="w60P col6">类型:<span>{{data.type}}</span></span></p>
			<p class="col6">当前价格:<span>{{data.ends}}</p>
			<p class="w60P col6">涨幅:<span>{{data.ranges}}</span></p>
			<p class="col6">时间:<span>{{data.day}}-{{data.hour}}</span></p>
		</li>
	</ul>
</template>
<script>
	export default {
	    props:['items']
	}
</script>