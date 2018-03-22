<template>
	<ul class="tabTitle">
		<span v-if="idx==0">
			实时数据
		</span>
		<span v-else="">
			最近{{idx}} 天数据
		</span>
		<!--<li v-for='(item,index) of items' :class='{on : index === idx }' @click="$router.push(item.push)">{{item.name}}</li>-->
	</ul>
</template>
<script>
	export default {
	    data () {
	        return {
	            items:[
	            	{name:"最新推荐",	push:"/share/detailList"}
//	            	{name:"申请中",	push:"/user/borrowList/apply"},
//	            	{name:"待归还",	push:"/user/borrowList/repay"},
//	            	{name:"已结束",	push:"/user/borrowList/over"}
	            ]
	        }
	    },
	    props:['idx']
	}
</script>